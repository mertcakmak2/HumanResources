package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "working_concepts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkingConcept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String place;
}
