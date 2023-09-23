package uz.cluster.entity;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
public abstract class  AbstractReferenceModel extends Auditable {

    @Id
    @Hidden
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    public AbstractReferenceModel(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public AbstractReferenceModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
