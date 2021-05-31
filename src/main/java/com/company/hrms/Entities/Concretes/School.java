package com.company.hrms.Entities.Concretes;

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
    @NotNull
    private Date graduationDate;
    private boolean graduate;

    // TODO // ManyToOne private Resume resume

    private boolean isActive = true;
}
