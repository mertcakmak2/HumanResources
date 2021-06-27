package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    @NotNull
    private JobSeeker jobSeeker;

    private String github;

    private String linkedin;

    private String coverLetter;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private List<School> schools;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private List<Language> languages;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private List<Skill> skills;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    private boolean isActive = true;

    public Resume(@NotNull JobSeeker jobSeeker, String github, String linkedin, String coverLetter) {
        this.jobSeeker = jobSeeker;
        this.github = github;
        this.linkedin = linkedin;
        this.coverLetter = coverLetter;
    }
}
