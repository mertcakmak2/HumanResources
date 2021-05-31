package com.company.hrms.Entities.Concretes;

import com.company.hrms.Core.Entitites.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class JobSeeker extends User {

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @Column(name="nationality_id", unique = true)
    @NotBlank
    @NotNull
    private String nationalityId;

    @Column(unique = true)
    @NotBlank
    @NotNull
    private String mobilePhone;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull
    private Date birthDate;


}
