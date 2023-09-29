package uz.cluster.repository.form.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.reference.Attachment;
import uz.cluster.enums.Status;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {


    Attachment findByHashId(String id);

    List<Attachment> findAllByFileStorageStatus(Status fileStorageStatus);
}
