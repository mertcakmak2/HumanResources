package com.company.hrms.Entities.Concretes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "job_announces")
@Data
@NoArgsConstructor
public class JobAnnounce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    @NotNull
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    @NotNull
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "job_type_id")
    @NotNull
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "working_concept_id")
    @NotNull
    private WorkingConcept workingConcept;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull
    private City city;

    @ManyToOne
    @JoinColumn(name = "confirmer_system_user_id")
    private SystemUser confirmerSystemUser;

    private int minSalary;

    private int maxSalary;

    @NotNull
    @Min(1)
    private int positionCount;

    private LocalDate lastDateOfAppeal;

    @Column(updatable = false)
    private LocalDate announceDate = LocalDate.now();

    private Boolean isActive = false;
}
