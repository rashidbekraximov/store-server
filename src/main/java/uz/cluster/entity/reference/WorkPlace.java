package uz.cluster.entity.reference;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uz.cluster.entity.reference.AbstractReferenceModel;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
@Table(name = "work_place")
public class WorkPlace extends AbstractReferenceModel {
}