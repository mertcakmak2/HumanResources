package com.company.hrms.Entities.Dto.JobSeeker;

import com.company.hrms.Entities.Concretes.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDefaultReturnDto {

    private int id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String email;
    private User user;
}
