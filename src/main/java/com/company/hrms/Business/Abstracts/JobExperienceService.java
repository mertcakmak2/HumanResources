package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobExperience;

public interface JobExperienceService {

    DataResult<JobExperience> saveJobExperience(JobExperience jobExperience);
}
