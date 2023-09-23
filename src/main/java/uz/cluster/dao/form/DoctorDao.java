package uz.cluster.dao.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.entity.Doctor;
import uz.cluster.entity.Specialist;
import uz.cluster.entity.WorkPlace;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDao extends BaseDao {

    private String name;

    private String surName;

    private Specialist specialist;

    //Image
    private String workExperience;

    private WorkPlace workPlace;

    private double mettingPrice;

    private double beginWork;

    private double endWork;

    private int specialistId;

    private int workPlaceId;

    public Doctor copy(DoctorDao dao) {
        Doctor doctor = new Doctor();
        doctor.setId((int) dao.getId());
        doctor.setName(dao.getName());
        doctor.setSurName(dao.getSurName());
        doctor.setSpecialist(dao.getSpecialist());
        doctor.setSpecialistId(dao.getSpecialistId());
        doctor.setWorkPlaceId(dao.getWorkPlaceId());
        doctor.setWorkPlace(dao.getWorkPlace());
        doctor.setWorkExperience(dao.getWorkExperience());
        doctor.setWorkPlace(dao.getWorkPlace());
        doctor.setMettingPrice(dao.getMettingPrice());
        doctor.setBeginWork(dao.getBeginWork());
        doctor.setEndWork(dao.getEndWork());
        return doctor;
    }

}
