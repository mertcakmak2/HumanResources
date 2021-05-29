package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.ImageService;
import com.company.hrms.DataAccess.Abstracts.ImageDao;
import com.company.hrms.Entities.Concretes.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageManager implements ImageService {

    private final ImageDao imageDao;

    public byte[] findImageByUserId(int userId) throws IOException {
        return imageDao.findByUserId(userId).getBytes();
    }

    @Override
    public Image saveUserImage(MultipartFile userImage, int userId) throws IOException, InterruptedException {
        byte[] bytes = StreamUtils.copyToByteArray(userImage.getInputStream());
        return imageDao.save(new Image( userId, bytes));
    }

    @Transactional
    @Override
    public String deleteUserImage(int userId) {
        imageDao.deleteUsersByUserId(userId);
        return "Fotoğraf başarıyla silindi userId="+userId;
    }
}
