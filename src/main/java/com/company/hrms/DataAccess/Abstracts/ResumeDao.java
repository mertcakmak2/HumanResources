package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDao extends JpaRepository<Resume, Integer> {

    Resume findByJobSeeker_Id(int jobSeekerId);

}
