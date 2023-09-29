package uz.cluster.dao.reference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.cluster.dao.form.DoctorDao;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadContext {

    private DoctorDao doctor;

    private MultipartFile multipartFile;
}
