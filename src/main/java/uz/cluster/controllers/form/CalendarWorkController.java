package uz.cluster.controllers.form;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.cluster.entity.form.CalendarWork;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.services.form.CalendarWorkService;

@RestController
@RequestMapping("api/")
@Tag(name = "Calendar", description = "Calendar ustida ammalar")
@RequiredArgsConstructor
public class CalendarWorkController {

    private final CalendarWorkService calendarWorkService;

    @GetMapping("calendar/{date}")
    public ResponseEntity<?> getById(@PathVariable String date) {
        CalendarWork dao = calendarWorkService.getByDate(date);
        if (dao == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dao);
    }

    @PostMapping("calendar/save")
    public ResponseEntity<?> save(@RequestBody CalendarWork dao) {
        ApiResponse apiResponse = calendarWorkService.add(dao);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}