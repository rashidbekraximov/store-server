package uz.cluster.util;

import uz.cluster.component.UserComponent;
import uz.cluster.configuration.SpringSecurityAuditorAware;
import uz.cluster.entity.auth.User;

public class GlobalParams {

    public GlobalParams() {
    }

    public static User getCurrentUser() {
        SpringSecurityAuditorAware s = new SpringSecurityAuditorAware();
        if (s.getCurrentAuditor().isPresent() && s.getCurrentAuditor().get() != -1){
            return UserComponent.getById(s.getCurrentAuditor().get());
        }
        if (s.getCurrentUser().isPresent()){
            return s.getCurrentUser().get();
        }
        return new User();
    }
}
