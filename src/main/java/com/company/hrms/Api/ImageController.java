package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ImageService;
import com.company.hrms.Business.Abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ImageController {

    private final ImageService imageService;

    @GetMapping ("/{userId}")
    public File findImageById(@PathVariable int userId) {
        return imageService.findImageByUserId(userId);
    }

    @PostMapping("/{userId}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable int userId) throws IOException {
        return imageService.saveUserImage(file, userId);
    }

    @DeleteMapping ("/{userId}")
    public String deleteImage(@PathVariable int userId) {
        return imageService.deleteUserImage(userId);
    }
}
