package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    byte[] findImageByUserId(int userId) throws IOException;
    Image saveUserImage(MultipartFile userImage, int userId) throws IOException, InterruptedException;
    String deleteUserImage(int userId);
}
