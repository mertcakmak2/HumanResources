package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

}
