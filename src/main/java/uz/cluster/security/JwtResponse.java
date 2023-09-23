package uz.cluster.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private boolean success;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String systemRoleName;
    private boolean enabled;
}
