package com.fanficApp.service;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.fanficApp.entity.Image;
import com.fanficApp.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary = Singleton.getCloudinary();

    @Autowired
    ImageRepo imageRepo;

    public Image uploadImage(MultipartFile file) throws IOException {
        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Image image = new Image();
        image.setPublicId(result.get("public_id").toString());
        image.setUrl(result.get("url").toString());
        imageRepo.save(image);
        return image;

    }

}
