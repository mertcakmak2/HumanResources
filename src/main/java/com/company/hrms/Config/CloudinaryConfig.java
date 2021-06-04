package com.company.hrms.Config;

import com.cloudinary.Cloudinary;
import com.company.hrms.HrmsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private String cloudName = "dr3qzenoh";
    private String apiKey = "422435649336317";
    private String apiSecret = "v_zE-GunmVoXENw-1eM05AAkTIA";

    @Bean
    public Cloudinary cloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        final Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
