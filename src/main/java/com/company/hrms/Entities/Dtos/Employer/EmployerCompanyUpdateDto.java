package com.company.hrms.Entities.Dtos.Employer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployerCompanyUpdateDto {

    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String companyWebSite;

    @NotNull
    @NotBlank
    private String companyEmail;
}
