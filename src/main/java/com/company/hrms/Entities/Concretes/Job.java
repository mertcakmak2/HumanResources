package com.company.hrms.Entities.Concretes;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "job_announcement")
@Data
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    //Zorunlu
    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    //Zorunlu
    private String jobDescription;

    //Zorunlu
    private String city;

    private int minSalary;

    private int maxSalary;

    //Zorunlu
    private int positionCount;

    private LocalDate lastDateOfAppeal;

    private LocalDate announceDate = LocalDate.now();

    private Boolean isActive;
}
