package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobExperience;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

    List<JobExperience> findByResume_Id(int resumeId, Sort sort);
}
