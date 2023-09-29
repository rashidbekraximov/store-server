package uz.cluster.repository.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.reference.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist,Integer> {
}
