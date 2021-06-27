package com.company.hrms.Entities.Concretes;

import com.company.hrms.Entities.Dtos.Employer.EmployerCompanyUpdateDto;
import com.company.hrms.Entities.Dtos.Resume.ResumeSaveDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employer_update_request")
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
public class EmployerUpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private EmployerCompanyUpdateDto employerCompanyUpdateDto;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    @Column(updatable = false)
    private Date createdAt = new Date();

    private Boolean isConfirmed = false;

    private Date confirmDate;

    public EmployerUpdateRequest(EmployerCompanyUpdateDto employerCompanyUpdateDto, Employer employer) {
        this.employerCompanyUpdateDto = employerCompanyUpdateDto;
        this.employer = employer;
    }
}
