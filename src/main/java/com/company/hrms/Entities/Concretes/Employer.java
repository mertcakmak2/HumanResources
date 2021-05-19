package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employers")
@Getter
@Setter
@NoArgsConstructor
public class Employer {

    @SequenceGenerator(name = "employer_sequence", sequenceName = "employer_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_sequence")
    private int id;

    private String firstName;

    private String lastName;

    private String companyEmail;

    private String password;

    private String companyName;

    private String companyWebSite;

    private String mobilePhone;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
