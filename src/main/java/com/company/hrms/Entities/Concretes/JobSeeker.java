package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "job_seekers")
@Getter
@Setter
@NoArgsConstructor
public class JobSeeker{

    @SequenceGenerator(name = "job_seeker_sequence", sequenceName = "job_seeker_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seeker_sequence")
    private int id;

    private String firstName;

    private String lastName;

    @Column(name="nationality_id", unique = true)
    private String nationalityId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @Column(name="email", unique = true)
    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name="user_id")
    //@JsonIgnore
    private User user;

}
