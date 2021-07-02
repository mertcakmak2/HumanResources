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
@Table(name = "schools")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String schoolName;
    @NotNull
    @NotBlank
    private String department;
    @NotNull
    private Date beginDate;
    private Date graduationDate;
    private boolean graduate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @NotNull
    private Resume resume;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    private boolean isActive = true;

    public School(@NotNull @NotBlank String schoolName,
                  @NotNull @NotBlank String department,
                  @NotNull Date beginDate,
                  Date graduationDate,
                  boolean graduate,
                  @NotNull Resume resume) {
        this.schoolName = schoolName;
        this.department = department;
        this.beginDate = beginDate;
        this.graduationDate = graduationDate;
        this.graduate = graduate;
        this.resume = resume;
    }
}
