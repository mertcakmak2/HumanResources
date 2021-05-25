package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int userId;
    private byte[] bytes;

    @Column(name = "created_at")
    private Date createdAt = new Date();
    private Boolean isActive = true;

    public Image(int userId, byte[] bytes) {
        this.userId = userId;
        this.bytes = bytes;
    }
}
