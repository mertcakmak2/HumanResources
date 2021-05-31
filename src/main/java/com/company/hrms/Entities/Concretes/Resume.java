package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // TODO // ManyToOne private JobSeeker jobSeeker

    // TODO // OneToMany List<School> schools

    // TODO // OneToMany List<JobExpreince> expreinces

    // TODO // OneToMany List<Language> languages

    // TODO // OneToMany List<Skill> skills

    private boolean isActive = true;

}
