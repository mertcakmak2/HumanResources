package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobExperienceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.JobExperienceDao;
import com.company.hrms.DataAccess.Abstracts.ResumeDao;
import com.company.hrms.Entities.Concretes.JobExperience;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Dtos.JobExperience.JobExperienceSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;
    private final ResumeDao resumeDao;

    @Override
    public DataResult<JobExperience> saveJobExperience(JobExperienceSaveDto jobExperienceSaveDto) {
        Resume resume = resumeDao.findById(jobExperienceSaveDto.getResumeId())
                .orElseThrow(() -> new EntityNotFoundException("Özgeçmiş bulunamadı"));

        JobExperience jobExperience = new JobExperience(
                jobExperienceSaveDto.getCompanyName(),
                jobExperienceSaveDto.getPosition(),
                jobExperienceSaveDto.getBeginDate(),
                jobExperienceSaveDto.getEndDate(),
                jobExperienceSaveDto.isWorkingStatu(),
                resume);
        return new SuccessDataResult<JobExperience>(jobExperienceDao.save(jobExperience),
                "İş tecrübeleri eklendi.");
    }

    @Override
    public DataResult<JobExperience> updateJobExperience(JobExperience jobExperience) {
        JobExperience existJobExperience = findJobExperienceById(jobExperience.getId());

        existJobExperience = jobExperience;
        return new SuccessDataResult<JobExperience>(jobExperienceDao.save(existJobExperience), "Deneyim güncellendi.");
    }

    @Override
    public DataResult<List<JobExperience>> findAllJobExperiencesByResume_Id(int resumeId) {
        return new SuccessDataResult<List<JobExperience>>(
                jobExperienceDao.findByResume_IdAndIsActive(resumeId,true, Sort.by(Sort.Direction.DESC,"endDate")),
                "Özgeçmiş tecrübeleri listelendi.");
    }

    @Override
    public DataResult<JobExperience> deleteJobExperience(int jobExperienceId) {
        JobExperience jobExperience = findJobExperienceById(jobExperienceId);
        jobExperience.setActive(false);
        return new SuccessDataResult<JobExperience>(jobExperienceDao.save(jobExperience), "Tecrübe silindi.");
    }

    public JobExperience findJobExperienceById(int id){
        return jobExperienceDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Deneyim bulunamadı."));
    }

}
