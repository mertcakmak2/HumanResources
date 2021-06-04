package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobExperienceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.JobExperienceDao;
import com.company.hrms.Entities.Concretes.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;

    @Override
    public DataResult<JobExperience> saveJobExperience(JobExperience jobExperience) {
        return new SuccessDataResult<JobExperience>(jobExperienceDao.save(jobExperience),"İş tecrübesi eklendi.");
    }

}
