package com.company.hrms.Entities.Concretes;

import com.company.hrms.Core.Entitites.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Employer extends User {

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String companyName;

    @NotBlank
    @NotNull
    private String companyWebSite;

    @Column(unique = true)
    @NotBlank
    @NotNull
    private String mobilePhone;

    @OneToMany(mappedBy = "employer")
    @JsonIgnore
    private List<JobAnnounce> jobAnnounces;

    @Column(name = "created_at")
    private Date createdAt = new Date();
    private Boolean isActive = true;

}
