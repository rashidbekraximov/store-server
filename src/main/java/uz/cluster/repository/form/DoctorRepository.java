package uz.cluster.repository.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.form.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
