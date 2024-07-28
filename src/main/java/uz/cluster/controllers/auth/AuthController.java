package uz.cluster.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.services.auth.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController  {

    private final UserService userService;
}