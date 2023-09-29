package uz.cluster.entity.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor_hour")
public class DoctorHour {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @JsonIgnore
    @Column(name="calendar_id")
    private long calendarId;

    @Transient
    private int doctorId;

    @Transient
    List<HourDescription> hourDescriptions = new ArrayList<>();
}
