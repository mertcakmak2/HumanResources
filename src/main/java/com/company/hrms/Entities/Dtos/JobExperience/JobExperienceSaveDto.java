package com.company.hrms.Entities.Dtos.JobExperience;

import com.company.hrms.Entities.Concretes.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobExperienceSaveDto {

    @NotNull
    @NotBlank
    private String companyName;
    @NotNull
    @NotBlank
    private String position;
    @NotNull
    private Date beginDate;

    private Date endDate;
    private boolean workingStatu;

    private int resumeId;
}
