package uz.cluster.services.form;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.cluster.entity.form.CalendarWork;
import uz.cluster.entity.form.Doctor;
import uz.cluster.entity.form.DoctorHour;
import uz.cluster.entity.form.HourDescription;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.repository.form.CalendarWorkRepository;
import uz.cluster.repository.form.DoctorHourRepository;
import uz.cluster.repository.form.DoctorRepository;
import uz.cluster.repository.form.HourDescriptionRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarWorkService {

    private final CalendarWorkRepository calendarWorkRepository;

    private final DoctorHourRepository doctorHourRepository;

    private final DoctorRepository doctorRepository;

    private final HourDescriptionRepository hourDescriptionRepository;

    public CalendarWork getByDate(String date){
        Optional<CalendarWork> optional = calendarWorkRepository.findByDate(LocalDate.parse(date));
        if (optional.isPresent()){
            optional.get().setDoctorHours(doctorHourRepository.findAllByCalendarId(optional.get().getId()));
            for (DoctorHour hour : optional.get().getDoctorHours()){
                hour.setHourDescriptions(hourDescriptionRepository.findAllByDoctorHourId(hour.getId()));
            }
            return optional.get();
        }else{
            return new CalendarWork();
        }
    }

    @Transactional
    public ApiResponse add(CalendarWork calendarWork) {

        CalendarWork saved = calendarWorkRepository.save(calendarWork);

        for (DoctorHour doctorHour : calendarWork.getDoctorHours()){
            if (doctorHour.getDoctorId() != 0){
                return new ApiResponse(true, "Doktor tanlanmadi !");
            }
            Optional<Doctor> doctor = doctorRepository.findById(doctorHour.getDoctorId());
            doctor.ifPresent(doctorHour::setDoctor);

            doctorHour.setCalendarId((int) saved.getId());
            DoctorHour doctorHour1 = doctorHourRepository.save(doctorHour);
            for (HourDescription hour : doctorHour.getHourDescriptions()){
                hour.setDoctorHourId(doctorHour1.getId());
                hourDescriptionRepository.save(hour);
            }
        }
        return new ApiResponse(true, saved, "Muvafaqiyatli saqlandi !");
    }

}
