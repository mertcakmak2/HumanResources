package com.company.hrms.Entities.Concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_users")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class SystemUser extends User implements Serializable {

    private String firstName;
    private String lastName;
    private String position;
    @Column(unique = true)
    private String mobilePhone;

}
