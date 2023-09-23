package uz.cluster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
