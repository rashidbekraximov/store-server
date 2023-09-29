package uz.cluster.entity.reference;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
@Table(name = "specialist")
public class Specialist extends AbstractReferenceModel {
}
