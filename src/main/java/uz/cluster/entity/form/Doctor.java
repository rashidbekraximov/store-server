package uz.cluster.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.entity.reference.Specialist;
import uz.cluster.entity.reference.WorkPlace;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(generator = "doctor_sq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "doctor_sq", sequenceName = "doctor_sq", allocationSize = 1)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sur_name")
    private String surName;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @Column(name = "work_experince")
    private String workExperience;

    @ManyToOne
    @JoinColumn(name = "work_place_id")
    private WorkPlace workPlace;

    @Column(name = "meeting_price")
    private double mettingPrice;

    @Column(name = "begin_work")
    private double beginWork;

    @Column(name = "end_work")
    private double endWork;

    @Transient
    private int specialistId;

    @Transient
    private int workPlaceId;
}