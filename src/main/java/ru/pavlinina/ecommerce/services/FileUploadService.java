package ru.pavlinina.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sofia Pavlinina
 */
public interface FileUploadService {

    public String upload(MultipartFile file);
}
