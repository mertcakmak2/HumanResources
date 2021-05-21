package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.ImageService;
import com.company.hrms.DataAccess.Abstracts.ImageDao;
import com.company.hrms.Entities.Concretes.Image;
import com.company.hrms.Entities.Concretes.User;
import lombok.RequiredArgsConstructor;
import org.apache.james.mime4j.dom.Multipart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageManager implements ImageService {

    private final ImageDao imageDao;

    @Override
    public File findImageByUserId(int userId) {
        String imagePath = imageDao.findByUserId(userId).getImagePath();
        File image = new File(imagePath);
        return image;
    }

    @Override
    public String saveUserImage(MultipartFile userImage, int userId) throws IOException {
        String FILE_DIRECTORY = "src/main/resources/images/"+userImage.getOriginalFilename();
        File image = new File(FILE_DIRECTORY);
        image.createNewFile();
        FileOutputStream fos =new FileOutputStream(image);
        fos.write(userImage.getBytes());
        fos.close();
        imageDao.save(new Image(FILE_DIRECTORY,userId));
        return FILE_DIRECTORY;
    }

    @Override
    public String deleteUserImage(int userId) {

        // TODO

        return null;
    }
}
