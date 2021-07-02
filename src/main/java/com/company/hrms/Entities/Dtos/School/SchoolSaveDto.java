package com.company.hrms.Entities.Dtos.School;

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
public class SchoolSaveDto {

    @NotBlank
    private String schoolName;
    @NotBlank
    private String department;
    @NotNull
    private Date beginDate;
    private Date graduationDate;
    private boolean graduate;
    @NotNull
    private int resumeId;
}
