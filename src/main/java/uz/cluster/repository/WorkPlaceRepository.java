package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.WorkPlace;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace,Integer> {
}
