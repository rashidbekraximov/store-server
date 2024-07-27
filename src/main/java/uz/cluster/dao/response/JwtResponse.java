package uz.cluster.dao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private boolean success;
    private String firstName;
    private String login;
    private String systemRoleName;
    private boolean enabled;
}