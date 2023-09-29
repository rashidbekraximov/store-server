package uz.cluster.services.reference;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.cluster.entity.reference.Attachment;
import uz.cluster.enums.Status;
import uz.cluster.dao.response.ApiResponse;
import uz.cluster.repository.form.reference.AttachmentRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    private final Hashids hashids;

    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public ApiResponse save(MultipartFile multipartFile) {
        Attachment fileStorage = new Attachment();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(Status.PASSIVE);
        attachmentRepository.save(fileStorage);

        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d/", this.uploadFolder,
                1900 + now.getYear(), 1 + now.getMonth(), now.getDate()));
        if (!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("aytilgan papkalar yaratildi");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate(),
                fileStorage.getHashId(),
                fileStorage.getExtension()));
        attachmentRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ApiResponse(true,  "Muvafaqiyatli saqlandi !");
    }

    @Transactional(readOnly = true)
    public Attachment findByHashId(String hashId){
        return attachmentRepository.findByHashId(hashId);
    }

    public void delete(String hashId){
        Attachment fileStorage = findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.uploadFolder, fileStorage.getUploadPath()));
        if (file.delete()){
            attachmentRepository.delete(fileStorage);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAllDraft(){
        List<Attachment> fileStorageList = attachmentRepository.findAllByFileStorageStatus(Status.PASSIVE);
        fileStorageList.forEach(fileStorage -> {
            delete(fileStorage.getHashId());
        });
    }

    private String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
