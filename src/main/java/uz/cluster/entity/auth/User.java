package uz.cluster.entity.auth;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.cluster.entity.Auditable;
import uz.cluster.enums.Status;
import uz.cluster.enums.auth.SystemRoleName;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class User extends Auditable  implements UserDetails {
    @Hidden
    @Id
    @SequenceGenerator(allocationSize = 1, name = "users_sq", sequenceName = "users_sq")
    @GeneratedValue(generator = "users_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(10) default 'ACTIVE'")
    private Status status = Status.ACTIVE;

    @Column
    private boolean enabled = true; //Mail checking turned off

    @Column
    private boolean accountNonExpired = true;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialsNonExpired = true;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "system_role_name")
    SystemRoleName systemRoleName;

    public User(
            String firstName, String lastName, String login,
            String password, String email,
            SystemRoleName systemRoleName, boolean accountNonLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.systemRoleName = systemRoleName;
        this.accountNonLocked = accountNonLocked;
        this.enabled = false;
    }

    public void copy(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.login = user.login;
        this.password = user.password;
        this.email = user.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.systemRoleName == null) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(SystemRoleName.SYSTEM_ROLE_SUPER_ADMIN.name());
            return Collections.singleton(simpleGrantedAuthority);
        }
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.systemRoleName.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    public String getAuthority() {
        return null;
    }
}