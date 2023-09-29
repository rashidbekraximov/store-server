package uz.cluster.services.form;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.cluster.dao.form.DoctorDao;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.entity.form.Doctor;
import uz.cluster.entity.reference.Specialist;
import uz.cluster.entity.reference.WorkPlace;
import uz.cluster.repository.form.DoctorRepository;
import uz.cluster.repository.reference.SpecialistRepository;
import uz.cluster.repository.reference.WorkPlaceRepository;
import uz.cluster.services.reference.AttachmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecialistRepository specialistRepository;

    private final WorkPlaceRepository workPlaceRepository;

    private final AttachmentService attachmentService;

    private ModelMapper mapper;

    public List<DoctorDao> getList() {
        return doctorRepository.findAll().stream().map((user) -> mapper.map(user, DoctorDao.class))
                .collect(Collectors.toList());
    }

    public DoctorDao getById(int id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        } else {
            return  mapper.map(optional.get(), DoctorDao.class);
        }
    }

    @Transactional
    public ApiResponse add(DoctorDao doctorDao, MultipartFile request) {
        Doctor doctor = mapper.map(doctorDao, Doctor.class);

        Optional<WorkPlace> optionalWorkPlace = workPlaceRepository.findById(doctor.getWorkPlaceId());
        optionalWorkPlace.ifPresent(doctor::setWorkPlace);

        Optional<Specialist> optionalSpecialist = specialistRepository.findById(doctor.getSpecialistId());
        optionalSpecialist.ifPresent(doctor::setSpecialist);

        if (doctor.getId() != 0) {
            return edit(doctor);
        }

        ApiResponse apiResponse = attachmentService.save(request); //This is after uploading users file to system, results is ApiResponse obj

        if (apiResponse.isSuccess()){
            Doctor saved = doctorRepository.save(doctor);
            return new ApiResponse(true, saved, "Muvafaqiyatli saqlandi !");
        }else{
            return new ApiResponse(true, "Xatolik kelib chiqdi!");
        }
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
