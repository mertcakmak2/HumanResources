package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeDao extends JpaRepository<JobType, Integer> {
}
