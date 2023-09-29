package uz.cluster.dao.reference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultReference {

    private Integer id;

    private String name;

    private String location;

    private boolean enable;

    private String tableName;
}
