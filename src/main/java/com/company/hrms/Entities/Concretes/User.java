package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    private Boolean isConfirmed = false;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    //Soft Delete Field
    private Boolean isActive = true;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
