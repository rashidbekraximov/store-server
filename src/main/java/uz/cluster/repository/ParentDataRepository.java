package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cluster.entity.ParentData;

public interface ParentDataRepository extends JpaRepository<ParentData, Long> {
}