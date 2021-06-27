package com.company.hrms.Entities.Concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "favourite_job_announces")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteJobAnnounce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "job_announce_id")
    @NotNull
    private JobAnnounce jobAnnounce;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    @NotNull
    private JobSeeker jobSeeker;

    @Column(updatable = false)
    private Date createdAt = new Date();

    private boolean isActive = true;
}
