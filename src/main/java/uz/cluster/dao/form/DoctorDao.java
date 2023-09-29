package uz.cluster.dao.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.entity.reference.Specialist;
import uz.cluster.entity.reference.WorkPlace;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDao extends BaseDao {

    private String name;

    private String surName;

    private Specialist specialist;

    private String workExperience;

    private WorkPlace workPlace;

    private double mettingPrice;

    private double beginWork;

    private double endWork;

    private int specialistId;

    private int workPlaceId;
}
