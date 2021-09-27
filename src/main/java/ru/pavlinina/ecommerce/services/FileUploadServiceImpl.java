package ru.pavlinina.ecommerce.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * implementation of {@link ru.pavlinina.ecommerce.services.FileUploadService}
 * @author Sofia Pavlinina
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final String UPLOADED_FOLDER = System.getProperty("user.dir") +
            "\\src\\main\\resources\\images\\";

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty())
            return null;

        String fileName = null;
        try {
            fileName = generateFileName(file.getOriginalFilename());

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/resources/" + fileName;

    }

    /**
     * method for generating unique file name
     * @param file - current name of file
     * @return new file name
     */
    private String generateFileName(String file) {
        String ext = file.substring(file.lastIndexOf("."));
        return System.currentTimeMillis() + ext;
    }

}
