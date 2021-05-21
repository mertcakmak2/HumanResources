package com.company.hrms.Business.Abstracts;

import org.apache.james.mime4j.dom.Multipart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    File findImageByUserId(int userId);
    String saveUserImage(MultipartFile userImage, int userId) throws IOException;
    String deleteUserImage(int userId);
}
