package com.company.hrms.Entities.Concretes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    private String jobDescription;

    private String city;

    private int minSalary;

    private int maxSalary;

    private int positionCount;

    private LocalDate lastDateOfAppeal;

    private LocalDate announceDate = LocalDate.now();

    private Boolean isActive = true;
}
