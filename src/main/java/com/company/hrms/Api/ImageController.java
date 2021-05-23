package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ImageService;
import com.company.hrms.Entities.Concretes.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImage(@PathVariable int userId) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.findImageByUserId(userId));
    }

    @PostMapping("/{userId}")
    public Image uploadProfileImage(@RequestParam("file") MultipartFile file, @PathVariable int userId) throws IOException, InterruptedException {
        return imageService.saveUserImage(file, userId);
    }

    @DeleteMapping ("/{userId}")
    public String deleteProfileImage(@PathVariable int userId) {
        return imageService.deleteUserImage(userId);
    }
}
