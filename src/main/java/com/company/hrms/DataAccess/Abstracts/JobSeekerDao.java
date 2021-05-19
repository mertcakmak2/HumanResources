package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

    //Optional<JobSeeker> findByEmail(String email);
}
