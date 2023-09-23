package uz.cluster.controllers.references;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.cluster.dao.auth.ChangePassword;
import uz.cluster.services.auth.UserService;
import uz.cluster.payload.auth.UserDTO;
import uz.cluster.payload.response.ApiResponse;
import uz.cluster.util.GlobalParams;

/*
 * User bo'yicha controller
 */
@RestController
@RequestMapping(path = "/api/")
@Tag(name = "Users", description = "User Ustida amallar")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Userlar ro'yxati")
    @GetMapping("users")
    public HttpEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Yangi Userni kiritish")
    @PostMapping("user/save")
    public HttpEntity<?> addUser(@RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = null;
        if (userDTO.getId() == 0){
            apiResponse = userService.add(userDTO);
        }else {
            apiResponse = userService.edit(userDTO,userDTO.getId());
        }
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @Operation(summary = "Userni o'chirib tashlash")
    @DeleteMapping(path = "user/{user_id}")
    public HttpEntity<?> deleteUser(@PathVariable("user_id") int userId) {
        ApiResponse apiResponse = userService.delete(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @Operation(summary = "User rolini unique orqali olish")
    @GetMapping(path = "user/{user_id}")
    public HttpEntity<?> getById(@PathVariable("user_id") int userId) {
        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }

    @Operation(summary = "User rolini unique orqali olish")
    @PostMapping(path = "user/change-password")
    public HttpEntity<?> getById(@RequestBody ChangePassword password) {
        return new ResponseEntity<>(userService.changePassword(password, GlobalParams.getCurrentUser().getId()), HttpStatus.OK);
    }
}
