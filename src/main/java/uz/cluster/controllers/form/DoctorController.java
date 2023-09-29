package uz.cluster.controllers.form;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.cluster.dao.form.DoctorDao;
import uz.cluster.dao.reference.UploadContext;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.services.form.DoctorService;

import java.util.List;


@RestController
@RequestMapping("api/")
@Tag(name = "Doctors", description = "Doktorlar formasi ustida ammalar")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("doctors")
    public ResponseEntity<List<DoctorDao>> getList() {
        return ResponseEntity.ok(doctorService.getList());
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        DoctorDao dao = doctorService.getById(id);
        return ResponseEntity.status(dao == null ? HttpStatus.CONFLICT : HttpStatus.OK).body(dao);
    }

    @PostMapping(value = "doctor/save")
    public ResponseEntity<?> save(@RequestBody UploadContext dao) {
        ApiResponse apiResponse = doctorService.add(dao.getDoctor(),dao.getMultipartFile());
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("doctor/delete/{id}")
    public ResponseEntity<?> save(@PathVariable int id) {
        ApiResponse apiResponse = doctorService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
