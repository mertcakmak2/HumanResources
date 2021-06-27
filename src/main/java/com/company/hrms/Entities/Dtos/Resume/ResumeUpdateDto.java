package com.company.hrms.Entities.Dtos.Resume;

import com.company.hrms.Entities.Concretes.JobSeeker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUpdateDto {

    @NotNull
    @NotBlank
    private int id;
    private String github;
    private String linkedin;
    private String coverLetter;
}
