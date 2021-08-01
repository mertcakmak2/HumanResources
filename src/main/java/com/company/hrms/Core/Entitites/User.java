package com.company.hrms.Core.Entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Email
    @NotBlank
    @NotNull
    private String email;

    @Column(name="password", nullable = false)
    @NotBlank
    @NotNull
    @JsonIgnore
    private String password;

    private Boolean isConfirmed = false;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    //Soft Delete Field
    private Boolean isActive = true;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
