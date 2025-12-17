package com.sec.car_selling.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FileService {

    static String UPLOAD_FOLDER = "upload";

    public String uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return null;
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (!originalFilename.isEmpty()) {
                int dotIndex = originalFilename.lastIndexOf('.');
                if (dotIndex > 0) {
                    extension = originalFilename.substring(dotIndex);
                }
            }
            String newFileName = UUID.randomUUID() + extension;

            Path uploadPath = Paths.get(UPLOAD_FOLDER).toAbsolutePath();
            File uploadDir = uploadPath.toFile();

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(uploadDir, newFileName);
            file.transferTo(destination);

            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

            return baseUrl + "/assets/" + newFileName;
        } catch (Exception e) {
            return null;
        }
    }
}
