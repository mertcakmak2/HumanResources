package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "system_users")
@Getter
@Setter
@NoArgsConstructor
public class SystemUser {

    @SequenceGenerator(name = "system_user_sequence", sequenceName = "system_user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_user_sequence")
    private int id;

    @Column(name="email", unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String position;

    private String password;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}
