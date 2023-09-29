package uz.cluster.configuration;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.cluster.entity.auth.User;
import uz.cluster.dao.SessionDto;
import uz.cluster.dao.response.DataDto;
import uz.cluster.dao.auth.LoginDao;
import uz.cluster.dao.response.JwtResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDao loginDao = new ObjectMapper().readValue(request.getReader(), LoginDao.class);
            log.info("Username is: {}", loginDao.getLogin());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDao.getLogin(), loginDao.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException, IOException {
        User user = (User) authentication.getPrincipal();
        Date expiryForAccessToken = JwtUtils.getExpiry();

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForAccessToken)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim("activeUserId", user.getId())
                .sign(JwtUtils.getAlgorithm());

        JwtResponse jwtResponse = new JwtResponse(true, user.getFirstName(),
                user.getLastName(),
                user.getEmail(), user.getLogin(),
                user.getSystemRoleName().name(),true
        );

        SessionDto sessionDto = SessionDto.builder()
                .accessToken(accessToken)
                .accessTokenExpiry(expiryForAccessToken.getTime())
                .user(jwtResponse)
                .issuedAt(System.currentTimeMillis())
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new DataDto<>(sessionDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        new ObjectMapper().writeValue(response.getOutputStream(),response);
    }
}