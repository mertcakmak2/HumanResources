package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
}
