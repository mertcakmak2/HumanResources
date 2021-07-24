package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer> {

    boolean existsByEmail(String email);
    @Query("from Employer where email=:email and isConfirmed=true and isActive=true")
    Employer findByEmail(String email);
}
