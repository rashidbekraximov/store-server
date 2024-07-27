package uz.cluster.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.dao.auth.UserDao;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.entity.auth.User;
import uz.cluster.services.auth.UserService;
import uz.cluster.dao.auth.LoginDao;
import uz.cluster.util.GlobalParams;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController  {

    private final UserService userService;

    @PostMapping("/api/auth/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginDao loginDao) {
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/api/account")
    public HttpEntity<?> retriveAccount(){
        User user = GlobalParams.getCurrentUser();
        return ResponseEntity.status(user.getId() != 0 ? 200 : 403).body(user);
    }

    @PostMapping("/api/auth/register")
    public HttpEntity<?> addUser(@RequestBody UserDao userDao) {
        ApiResponse apiResponse = userService.add(userDao);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
}