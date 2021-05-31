package com.company.hrms.Entities.Concretes;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    @NotNull
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    @NotNull
    private Employer employer;

    @NotBlank
    @NotNull
    private String jobDescription;

    @NotBlank
    @NotNull
    private String city;

    private int minSalary;

    private int maxSalary;

    @NotNull
    @Min(1)
    private int positionCount;

    private LocalDate lastDateOfAppeal;

    private LocalDate announceDate = LocalDate.now();

    private Boolean isActive = true;
}
