package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist,Integer> {
}
