package uz.cluster.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.security.JwtResponse;
import uz.cluster.services.auth.UserService;
import uz.cluster.payload.auth.LoginDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController  {

    private final UserService userService;

    @PostMapping("/api/auth/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginDTO loginDTO){
        JwtResponse jwtResponse = userService.login(loginDTO);
        return ResponseEntity.status(jwtResponse.isSuccess() ? 200 : 403).body(jwtResponse);
    }


}