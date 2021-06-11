package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobPosition;

import java.util.List;

public interface JobPositionService {

    DataResult<JobPosition> saveJobPosition(JobPosition jobPosition);
    DataResult<List<JobPosition>> findAllJobPositions();
    String deleteJobPosition(int id);
}
