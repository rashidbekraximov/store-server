package uz.cluster.repository.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.form.DoctorHour;

import java.util.List;

@Repository
public interface DoctorHourRepository extends JpaRepository<DoctorHour,Integer> {

    List<DoctorHour> findAllByCalendarId(long calendarId);
}
