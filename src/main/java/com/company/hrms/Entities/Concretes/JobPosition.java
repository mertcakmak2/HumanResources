package com.company.hrms.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "job_positions")
@Getter
@Setter
@NoArgsConstructor
public class JobPosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String positionName;

   /* @ManyToOne
    @JoinColumn(name = "system_user_id")
    @JsonIgnore
    private SystemUser systemUser;*/

    @Column(name = "created_at")
    private Date createdAt = new Date();
    private Boolean isActive = true;

}
