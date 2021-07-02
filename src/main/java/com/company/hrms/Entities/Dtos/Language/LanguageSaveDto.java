package com.company.hrms.Entities.Dtos.Language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSaveDto {

    @NotBlank
    private String languageName;

    @Range(min = 1, max = 5)
    private int languageLevel;

    @NotNull
    private int resumeId;
}
