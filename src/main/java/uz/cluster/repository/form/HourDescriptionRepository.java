package uz.cluster.repository.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.form.HourDescription;

import java.util.List;

@Repository
public interface HourDescriptionRepository extends JpaRepository<HourDescription,Integer> {

    List<HourDescription> findAllByDoctorHourId(int doctorHourId);

}
