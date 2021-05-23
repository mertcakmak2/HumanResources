package com.company.hrms.Entities.Dto.JobSeeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerRegisterDto {

    private String firstName;
    private String lastName;
    private String nationalityId;
    private Date birthDate;
    private String email;
    private String password;
}
