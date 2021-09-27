package ru.pavlinina.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * service for uploading image file paths for product entities
 * @author Sofia Pavlinina
 */
public interface FileUploadService {

    /**
     * method for getting full path of necessary image file
     * @param file file, which path is needed
     * @return string with path to necessary file
     */
    public String upload(MultipartFile file);
}
