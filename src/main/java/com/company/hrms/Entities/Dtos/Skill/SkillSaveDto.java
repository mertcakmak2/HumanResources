package com.company.hrms.Entities.Dtos.Skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillSaveDto {

    @NotBlank
    private String skillName;
    @NotNull
    private int resumeId;
}
