package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.JobPosition;

import java.util.List;

public interface JobPositionService {

    JobPosition saveJobPosition(JobPosition jobPosition);
    List<JobPosition> findAllJobPositions();
    String deleteJobPosition(int id);
}
