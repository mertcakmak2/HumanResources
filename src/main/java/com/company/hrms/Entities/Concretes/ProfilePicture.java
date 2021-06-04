package com.company.hrms.Entities.Concretes;

import com.company.hrms.Core.Entitites.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profile_pictures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String picturePath;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    private boolean isActive = true;

    public ProfilePicture(String picturePath) {
        this.picturePath = picturePath;
    }
}
