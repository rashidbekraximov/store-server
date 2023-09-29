package uz.cluster.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.dao.response.JwtResponse;
import uz.cluster.services.auth.UserService;
import uz.cluster.dao.auth.LoginDao;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController  {

    @PostMapping("/api/auth/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginDao loginDao) {
        return ResponseEntity.status(200).body("");
    }
}