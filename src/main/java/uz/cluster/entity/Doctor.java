package uz.cluster.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import uz.cluster.dao.form.DoctorDao;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited(withModifiedFlag = true)
@Table(name = "doctor")
public class Doctor extends Auditable {

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

    //Image
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


    public DoctorDao asDao() {
        DoctorDao dao = new DoctorDao();
        dao.setId(getId());
        dao.setName(getName());
        dao.setSurName(getSurName());
        dao.setSpecialist(getSpecialist());
        dao.setSpecialistId(getSpecialistId());
        dao.setWorkPlaceId(getWorkPlaceId());
        dao.setWorkPlace(getWorkPlace());
        dao.setWorkExperience(getWorkExperience());
        dao.setWorkPlace(getWorkPlace());
        dao.setMettingPrice(getMettingPrice());
        dao.setBeginWork(getBeginWork());
        dao.setEndWork(getEndWork());
        return dao;
    }
}