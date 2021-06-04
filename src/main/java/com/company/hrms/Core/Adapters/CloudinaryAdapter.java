package com.company.hrms.Core.Adapters;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.company.hrms.Core.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@Component
@Qualifier(value = "CloudinaryAdapter")
@RequiredArgsConstructor
public class CloudinaryAdapter implements ProfilePictureService {

    private final Cloudinary cloudinary;

    @Override
    public String saveProfilePicture(MultipartFile picture) {
        try {
            Map uploadResult = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
