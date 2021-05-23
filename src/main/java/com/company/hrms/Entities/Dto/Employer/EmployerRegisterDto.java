package com.company.hrms.Entities.Dto.Employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegisterDto {

    private String firstName;
    private String lastName;
    private String companyEmail;
    private String password;
    private String companyName;
    private String companyWebSite;
    private String mobilePhone;
}
