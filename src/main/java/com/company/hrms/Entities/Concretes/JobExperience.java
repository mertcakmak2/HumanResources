package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Date endDate;
    private boolean workingStatu;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    private boolean isActive = true;

    public JobExperience(@NotNull @NotBlank String companyName,
                         @NotNull @NotBlank String position,
                         @NotNull Date beginDate,
                         Date endDate,
                         boolean workingStatu,
                         Resume resume) {
        this.companyName = companyName;
        this.position = position;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.workingStatu = workingStatu;
        this.resume = resume;
    }
}
