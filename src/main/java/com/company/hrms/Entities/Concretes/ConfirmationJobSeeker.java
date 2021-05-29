package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "confirmation_job_seekers")
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationJobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(nullable = false, name = "register_confirm_token_id")
    private RegisterConfirmToken registerConfirmToken;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_seeker_id")
    private JobSeeker jobSeeker;

    public ConfirmationJobSeeker(RegisterConfirmToken registerConfirmToken, JobSeeker jobSeeker) {
        this.registerConfirmToken = registerConfirmToken;
        this.jobSeeker = jobSeeker;
    }

}
