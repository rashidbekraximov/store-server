package uz.cluster.dao.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.enums.Status;
import uz.cluster.enums.auth.SystemRoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private boolean accountNonLocked;
    @Enumerated(value = EnumType.STRING)
    private SystemRoleName systemRoleName;

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
