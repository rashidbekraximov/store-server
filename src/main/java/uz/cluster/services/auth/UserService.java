package uz.cluster.services.auth;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.cluster.dao.auth.ChangePassword;
import uz.cluster.dao.auth.UserDao;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.entity.auth.User;
import uz.cluster.enums.auth.SystemRoleName;
import uz.cluster.repository.auth.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    private final AttachmentService att;

    private ModelMapper mapper;

    @Transactional
    public ApiResponse add(UserDao userDao) { // This method is to add
        if (userRepository.existsByLoginAndIdNot(userDao.getLogin(), 0)) //This will check login is unique or not, if not it terminates from adding
            return new ApiResponse(false, "");

        User user = new User();
        user.setFirstName(userDao.getFirstName());
        user.setAccountNonLocked(true);
        user.setLogin(userDao.getLogin());
        user.setSystemRoleName(SystemRoleName.SYSTEM_ROLE_SUPER_ADMIN);
        user.setPassword(passwordEncoder.encode(userDao.getPassword())); //This sets user's password that is encrypted
        userRepository.save(user); //this saves user to database
        return new ApiResponse(true, user, "Muvaffaqiyatli saqlandi!");
    }


    @Transactional
    public ApiResponse edit(UserDao userDao, int id) {
        User user = mapper.map(userDao,User.class);

        ApiResponse apiResponse = new ApiResponse();
        if (userRepository.existsByLoginAndIdNot(user.getLogin(), user.getId())) //This will check login is unique or not, if not it terminates from adding
            return new ApiResponse(false, "");

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) //This check that there is such user, if not this statement terminates from adding
            return new ApiResponse(false, id, "Bu Id doir malumot topilmadi !");

        if (!userDao.getPassword().isEmpty()) //If user's password is changed, it again encrypts it and assigns to user
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        User editedUser = userRepository.save(user); //This will save User to DB
        apiResponse.setSuccess(true); //apiResponse status -> True or False
        apiResponse.setObject(editedUser); //this is User obj to give to Front-end
        apiResponse.setMessage(""); //ApiResponses message
        return apiResponse;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public UserDao getById(int id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> value.setPassword(""));
        return mapper.map(user.orElse(new User()),UserDao.class);
    }

    public ApiResponse delete(int id) {
        if (!userRepository.existsById(id))
            return new ApiResponse(false, "Foydalanuvchi mavjud emas !");
        userRepository.deleteById(id);
        return new ApiResponse(true, "Muvaffaqiyatli saqlandi!");
    }

    public ApiResponse changePassword(ChangePassword changePassword, int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            if (!(passwordEncoder.matches(changePassword.getOldPassword(), optionalUser.get().getPassword()))) {
                return ApiResponse.builder()
                        .message("Parollar mos emas !")
                        .isSuccess(false)
                        .build();
            } else {
                optionalUser.get().setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                userRepository.save(optionalUser.get());
            }
        } else {
            return new ApiResponse(false, "Eski parolni xato kiritingiz!");
        }
        return new ApiResponse(true, "Muvaffaqiyatli saqlandi!");
    }

    public ApiResponse checkPasswordAndUser(ChangePassword changePassword) {
        Optional<User> optionalUser = userRepository.findById(changePassword.getUserId());
        if (optionalUser.isPresent()) {
            if (!(passwordEncoder.matches(changePassword.getOldPassword(), optionalUser.get().getPassword())) && changePassword.getSystem_role_name().equals(optionalUser.get().getSystemRoleName().name())) {
                return ApiResponse.builder()
                        .message("Parollar yoki foydalanuvchi nomi mos emas !")
                        .isSuccess(false)
                        .build();
            } else {
                return new ApiResponse(true, "Muvaffaqiyatli kirdingiz!");
            }
        } else {
            return new ApiResponse(false, "Bu foydalanuvchiga doir ma'lumot topilmadi!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }
}
