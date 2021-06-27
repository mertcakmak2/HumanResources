package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerUpdateRequestDao extends JpaRepository<EmployerUpdateRequest, Integer> {

    EmployerUpdateRequest findByEmployer_Id(int employerId);

    EmployerUpdateRequest findByEmployer_IdAndIsConfirmed(int employerId, boolean isConfirmed);
}
