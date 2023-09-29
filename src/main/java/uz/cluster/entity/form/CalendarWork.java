package uz.cluster.entity.form;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calendar_work")
public class CalendarWork {


    @Id
    @GeneratedValue(generator = "calendar_work_sq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "calendar_work_sq", sequenceName = "calendar_work_sq", allocationSize = 1)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @Transient
    List<DoctorHour> doctorHours = new ArrayList<>();
}
