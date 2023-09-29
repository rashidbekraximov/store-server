package uz.cluster.repository.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.form.CalendarWork;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CalendarWorkRepository extends JpaRepository<CalendarWork,Long> {

    Optional<CalendarWork> findByDate(LocalDate date);
}
