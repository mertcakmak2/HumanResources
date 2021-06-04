package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobExperienceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.JobExperienceDao;
import com.company.hrms.Entities.Concretes.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;

    @Override
    public DataResult<JobExperience> saveJobExperience(JobExperience jobExperience) {
        return new SuccessDataResult<JobExperience>(jobExperienceDao.save(jobExperience),
                "İş tecrübesi eklendi.");
    }

    @Override
    public DataResult<List<JobExperience>> findByResume_Id(int resumeId) {
        return new SuccessDataResult<List<JobExperience>>(
                jobExperienceDao.findByResume_Id(resumeId,Sort.by(Sort.Direction.DESC,"endDate")));
    }

}
