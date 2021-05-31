package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "job_experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String companyName;
    @NotNull
    @NotBlank
    private String position;
    @NotNull
    private Date beginDate;
    @NotNull
    private Date endDate;
    private boolean workingStatu;

    // TODO // ManyToOne private Resume resume

    private boolean isActive = true;
}
