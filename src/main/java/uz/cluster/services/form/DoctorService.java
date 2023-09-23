package uz.cluster.services.form;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.cluster.dao.form.DoctorDao;
import uz.cluster.entity.Doctor;
import uz.cluster.entity.Specialist;
import uz.cluster.entity.WorkPlace;
import uz.cluster.payload.response.ApiResponse;
import uz.cluster.repository.DoctorRepository;
import uz.cluster.repository.SpecialistRepository;
import uz.cluster.repository.WorkPlaceRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecialistRepository specialistRepository;

    private final WorkPlaceRepository workPlaceRepository;

    public List<DoctorDao> getList() {
        return doctorRepository.findAll().stream().map(Doctor::asDao).collect(Collectors.toList());
    }

    public DoctorDao getById(int id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        } else {
            return optional.get().asDao();
        }
    }

    @Transactional
    public ApiResponse add(DoctorDao doctorDao) {
        Doctor doctor = doctorDao.copy(doctorDao);

        Optional<WorkPlace> optionalWorkPlace = workPlaceRepository.findById(doctor.getWorkPlaceId());
        optionalWorkPlace.ifPresent(doctor::setWorkPlace);

        Optional<Specialist> optionalSpecialist = specialistRepository.findById(doctor.getSpecialistId());
        optionalSpecialist.ifPresent(doctor::setSpecialist);

        if (doctor.getId() != 0) {
            return edit(doctor);
        }

        Doctor saved = doctorRepository.save(doctor);
        return new ApiResponse(true, saved, "Muvafaqiyatli saqlandi !");
    }

    @Transactional
    public ApiResponse edit(Doctor doctor) {
        Optional<Doctor> optional = doctorRepository.findById(doctor.getId());

        if (optional.isPresent()){
            Doctor edited = doctorRepository.save(doctor);
            return new ApiResponse(true, edited, "Muvaffaqiyatli tahrirlandi!");
        }else{
            return new ApiResponse(false, null, "Bu Id ga doir malumot topilmadi!");
        }
    }

    @Transactional
    public ApiResponse delete(int id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()){
            doctorRepository.deleteById(id);
            return new ApiResponse(true, "Muvaffaqiyatli o'chirildi!");
        }else{
            return new ApiResponse(false, "Bu Id ga doir malumot topilmadi!");
        }
    }
}
