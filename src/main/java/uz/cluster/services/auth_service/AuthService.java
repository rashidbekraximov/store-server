package uz.cluster.services.auth_service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.cluster.entity.auth.User;
import uz.cluster.enums.auth.SystemRoleName;
import uz.cluster.payload.auth.LoginDTO;
import uz.cluster.payload.auth.UserDTO;
import uz.cluster.payload.response.ApiResponse;
import uz.cluster.repository.UserRepository;
import uz.cluster.security.JwtProvider;
import uz.cluster.security.JwtResponse;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public ApiResponse add(UserDTO userDTO) { // This method is to add
        if (userRepository.existsByLoginAndIdNot(userDTO.getLogin(), 0)) //This will check login is unique or not, if not it terminates from adding
            return new ApiResponse(false, "");

        if (userRepository.existsByEmailAndIdNot(userDTO.getEmail(), 0)) //This will check that email is already exists or not, if not it terminates from adding
            return new ApiResponse(false, "");

        if (userDTO.getPassword().isEmpty()) //This check that user has passport or not, if not it terminates from adding
            return new ApiResponse(false, "");

        if (Period.between(userDTO.getBirthday(), LocalDate.now()).getYears() < 18)  //This check that user's age is not less than AGE_RESTRICTION, if not this statement terminates from adding
            return new ApiResponse(false, "");

        User user = new User(
                userDTO.getFirstName().substring(0, 1).toUpperCase(Locale.ROOT) + userDTO.getFirstName().toLowerCase().substring(1),
                userDTO.getLastName().substring(0, 1).toUpperCase(Locale.ROOT) + userDTO.getLastName().toLowerCase().substring(1),
                userDTO.getLogin(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getSystemRoleName(),
                userDTO.isAccountNonLocked()
        );

        if (
                user.getSystemRoleName() != SystemRoleName.SYSTEM_ROLE_FORM_MEMBER
                        && user.getSystemRoleName() != SystemRoleName.SYSTEM_ROLE_MEMBER
        )


        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); //This sets user's password that is encrypted
        userRepository.save(user); //this saves user to database
        return new ApiResponse(true, user, "");
    }


    @Transactional
    public ApiResponse edit(UserDTO userDTO,int id) {
        ApiResponse apiResponse = new ApiResponse();
        if (userRepository.existsByLoginAndIdNot(userDTO.getLogin(), userDTO.getId())) //This will check login is unique or not, if not it terminates from adding
            return new ApiResponse(false,"");

        if (userRepository.existsByEmailAndIdNot(userDTO.getEmail(), userDTO.getId())) //This will check that email is already exists or not, if not it terminates from adding
            return new ApiResponse(false, "");

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) //This check that there is such user, if not this statement terminates from adding
            return new ApiResponse(false, id, "");

        if (Period.between(userDTO.getBirthday(), LocalDate.now()).getYears() < 18) //This check that user's age is not less than 18, if not this statement terminates from adding
            return new ApiResponse(false, optionalUser.get(),"");

        User editingUser = optionalUser.get();
        editingUser.setFirstName(userDTO.getFirstName());
        editingUser.setLastName(userDTO.getLastName());
        editingUser.setLogin(userDTO.getLogin());
        editingUser.setSystemRoleName(userDTO.getSystemRoleName());

        if ( editingUser.getSystemRoleName() != SystemRoleName.SYSTEM_ROLE_FORM_MEMBER && editingUser.getSystemRoleName() != SystemRoleName.SYSTEM_ROLE_MEMBER )

        if (!userDTO.getPassword().isEmpty()) //If user's password is changed, it again encrypts it and assigns to user
            editingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User editedUser = userRepository.save(editingUser); //This will save User to DB
        apiResponse.setSuccess(true); //apiResponse status -> True or False
        apiResponse.setObject(editedUser); //this is User obj to give to Front-end
        apiResponse.setMessage(""); //ApiResponses message
        return apiResponse;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> value.setPassword(""));
        return user.orElse(new User());
    }

    public ApiResponse delete(int id) {
        if (!userRepository.existsById(id))
            return new ApiResponse(false,"");
        userRepository.deleteById(id);
        return new ApiResponse(true, "");
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }

    public JwtResponse login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getLogin(),
                    loginDTO.getPassword()
            )); //System checks user is exists or not
            User user = (User) authentication.getPrincipal(); //Casts Principal to User

            String token = jwtProvider.generateToken(user.getLogin());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);

            return new JwtResponse(
                    token,
                    true,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getLogin(),
                    user.getSystemRoleName().name(),
                    true
            );
        } catch (Exception exception) {
            return new JwtResponse(
                    null,
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    false
            );
        }
    }
}
