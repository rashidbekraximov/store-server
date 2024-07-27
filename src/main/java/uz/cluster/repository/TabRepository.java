package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.SubOpt;
import uz.cluster.entity.main.SubTab;
import uz.cluster.enums.Category;

import java.util.List;

@Repository
public interface TabRepository extends JpaRepository<SubTab,Integer> {

    @Query(value = "select * from sub_tab s where s.category = :category",nativeQuery = true)
    List<SubTab> findAllByCategory(@Param("category") String category);
}
