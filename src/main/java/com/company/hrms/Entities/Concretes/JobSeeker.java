package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class JobSeeker extends User{

    private String firstName;
    private String lastName;

    @Column(name="nationality_id", unique = true)
    private String nationalityId;

    @Column(unique = true)
    private String mobilePhone;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;


}
