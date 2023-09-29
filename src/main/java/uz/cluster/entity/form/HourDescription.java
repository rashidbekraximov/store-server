package uz.cluster.entity.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hour_description")
public class HourDescription {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "hour")
    private int hour;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "doctor_hour_id")
    private int doctorHourId;
}
