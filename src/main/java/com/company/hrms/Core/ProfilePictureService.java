package com.company.hrms.Core;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.ProfilePicture;
import org.apache.james.mime4j.dom.Multipart;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ProfilePictureService {

    String saveProfilePicture(MultipartFile profilePicture);
}
