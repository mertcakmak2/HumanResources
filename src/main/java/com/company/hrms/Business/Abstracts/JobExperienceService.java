package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {

    DataResult<JobExperience> saveJobExperience(JobExperience jobExperience);

    DataResult<JobExperience> updateJobExperience(JobExperience jobExperience);

    DataResult<List<JobExperience>> findAllJobExperiencesByResume_Id(int resumeId);
}
