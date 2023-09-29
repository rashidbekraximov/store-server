package uz.cluster.services.reference;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.cluster.configuration.SpringSecurityAuditorAware;
import uz.cluster.dao.reference.DefaultReference;
import uz.cluster.dao.reference.SimilarDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultReferenceService {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<SimilarDao> getReferenceItems(String viewName) {

        List<SimilarDao> references = new ArrayList<>();
        String sqlString = "select id,name,location,enable from " + viewName + " order by id";

        Query query = entityManager.createNativeQuery(sqlString);
        List resultList = query.getResultList();

        resultList.forEach(o -> {
            SimilarDao similarDao = new SimilarDao();
            Object[] refer = (Object[]) o;
            similarDao.setId((Integer) refer[0]);
            similarDao.setName((String) refer[1]);
            similarDao.setLocation((String) refer[2]);
            similarDao.setEnable((boolean) refer[3]);
            references.add(similarDao);
        });
        return references;
    }

    @Transactional
    public void save(DefaultReference reference) {
        Object createdByTable = entityManager.createNativeQuery("select table_name\n" +
                "from information_schema.columns\n" +
                "where table_schema not in ('information_schema', 'pg_catalog')\n" +
                "and column_name='created_by'\n" +
                "and table_name='" + reference.getTableName() + "'").getSingleResult();

        boolean isHasAuditable = false;

        if (createdByTable != null && createdByTable.equals(reference.getTableName()))
            isHasAuditable = true;

        SpringSecurityAuditorAware s = new SpringSecurityAuditorAware();
        int activeUserId = s.getCurrentAuditor().get();
        String query;

        if (isHasAuditable) {
            query = "INSERT INTO " + reference.getTableName() + "( name,location,enable,created_by, created_on, modified_by, modified_on) VALUES( :name,:location, :enable,:created_by, :created_on, :modified_by, :modified_on)";
        } else {
            query = "INSERT INTO " + reference.getTableName() + "(name,location,enable) VALUES( :name,:location :enable)";
        }
        Query refQuery = entityManager.createNativeQuery(query);
        refQuery.setParameter("name", reference.getName());
        refQuery.setParameter("location", reference.getLocation());
        refQuery.setParameter("enable", false);
        if (isHasAuditable) {
            refQuery.setParameter("created_by", activeUserId);
            refQuery.setParameter("created_on", LocalDateTime.now());
            refQuery.setParameter("modified_by", activeUserId);
            refQuery.setParameter("modified_on", LocalDateTime.now());
        }
        refQuery.executeUpdate();
    }
}
