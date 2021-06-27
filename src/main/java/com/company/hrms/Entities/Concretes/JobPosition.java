package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job_positions")
@Getter
@Setter
@NoArgsConstructor
public class JobPosition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String positionName;

    @OneToMany(mappedBy = "jobPosition")
    @JsonIgnore
    private List<JobAnnounce> jobAnnounces;

    @Column(name = "created_at")
    private Date createdAt = new Date();
    private Boolean isActive = true;

}
