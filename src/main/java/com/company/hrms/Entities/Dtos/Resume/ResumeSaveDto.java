package com.company.hrms.Entities.Dtos.Resume;

import com.company.hrms.Entities.Concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSaveDto {

    @NotNull
    private JobSeeker jobSeeker;
    private String github;
    private String linkedin;
    private String coverLetter;

}
