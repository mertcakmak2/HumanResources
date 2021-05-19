package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.ConfirmationJobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationJobSeekerDao extends JpaRepository<ConfirmationJobSeeker, Integer> {

    //ConfirmationJobSeeker findByToken(String token);
}
