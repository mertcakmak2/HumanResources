package com.company.hrms.Core;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ProfilePictureService {

    String saveProfilePicture(MultipartFile profilePicture);
}
