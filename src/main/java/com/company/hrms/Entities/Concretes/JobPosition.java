package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "job_positions")
@Getter
@Setter
@NoArgsConstructor
public class JobPosition {


    @SequenceGenerator(name = "job_position_sequence", sequenceName = "job_position_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_position_sequence")
    private int id;

    @Column(unique = true)
    private String positionName;

    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

}
