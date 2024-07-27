package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.Good;

@Repository
public interface GoodRepository extends JpaRepository<Good,Long> {
}
