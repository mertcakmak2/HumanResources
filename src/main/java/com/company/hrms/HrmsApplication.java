package com.company.hrms;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class HrmsApplication {

    private String cloudName = "dr3qzenoh";
    private String apiKey = "422435649336317";
    private String apiSecret = "v_zE-GunmVoXENw-1eM05AAkTIA";

    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        final Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
