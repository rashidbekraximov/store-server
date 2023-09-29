package uz.cluster.entity.reference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.enums.Status;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String extension;

    private Long fileSize;

    private String hashId;

    private String contentType;

    private String uploadPath;

    @Enumerated(EnumType.STRING)
    private Status fileStorageStatus;
}

