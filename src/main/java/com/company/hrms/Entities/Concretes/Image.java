package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @SequenceGenerator(name = "image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence")
    private int id;
    private String imagePath;

    @Column(unique = true)
    private int userId;

    private byte[] bytes;

    public Image(String imagePath, int userId, byte[] bytes) {
        this.imagePath = imagePath;
        this.userId = userId;
        this.bytes = bytes;
    }
}
