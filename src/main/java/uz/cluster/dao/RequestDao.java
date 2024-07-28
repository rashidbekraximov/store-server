package uz.cluster.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDao {

    private String facturaSessionId;

    private String mySoliqUz;

    private String route;

    private String ssoid;

    private String status;

}
