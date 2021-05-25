package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Employer extends User {

    private String firstName;

    private String lastName;

    private String companyName;

    private String companyWebSite;

    @Column(unique = true)
    private String mobilePhone;

    @Column(name = "created_at")
    private Date createdAt = new Date();
    private Boolean isActive = true;

}
