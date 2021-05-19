package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_employers")
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationEmployer {

    @SequenceGenerator(name = "confirmation_employer_sequence", sequenceName = "confirmation_employer_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_employer_sequence")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "register_confirm_token_id")
    private RegisterConfirmToken registerConfirmToken;

    @ManyToOne
    @JoinColumn(name = "confirmed_system_user_id")
    private SystemUser systemUser;

    @ManyToOne
    @JoinColumn(nullable = false, name = "employer_id")
    private Employer employer;

    public ConfirmationEmployer(RegisterConfirmToken registerConfirmToken, Employer employer) {
        this.registerConfirmToken = registerConfirmToken;
        this.employer = employer;
    }
}
