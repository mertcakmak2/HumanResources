package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobExperience;
import com.company.hrms.Entities.Dtos.JobExperience.JobExperienceSaveDto;

import java.util.List;

public interface JobExperienceService {

    DataResult<JobExperience> saveJobExperience(JobExperienceSaveDto jobExperienceSaveDto);

    DataResult<JobExperience> updateJobExperience(JobExperience jobExperience);

    DataResult<List<JobExperience>> findAllJobExperiencesByResume_Id(int resumeId);

    DataResult<JobExperience> deleteJobExperience(int jobExperienceId);
}
