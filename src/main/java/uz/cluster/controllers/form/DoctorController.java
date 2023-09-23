package uz.cluster.controllers.form;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.cluster.dao.form.DoctorDao;
import uz.cluster.payload.response.ApiResponse;
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
        if (dao == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dao);
    }

    @PostMapping("doctor/save")
    public ResponseEntity<?> save(@RequestBody DoctorDao dao) {
        ApiResponse apiResponse = doctorService.add(dao);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("doctor/delete/{id}")
    public ResponseEntity<?> save(@PathVariable int id) {
        ApiResponse apiResponse = doctorService.delete(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
