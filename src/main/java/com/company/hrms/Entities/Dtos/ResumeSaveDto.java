package com.company.hrms.Entities.Dtos;

import com.company.hrms.Entities.Concretes.JobSeeker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSaveDto {

    @NotNull
    private JobSeeker jobSeeker;
    private String profilePic;
    private String github;
    private String linkedin;


}
