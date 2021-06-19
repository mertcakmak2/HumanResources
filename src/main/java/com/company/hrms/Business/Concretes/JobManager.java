package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobService;
import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Result.SuccessResult;
import com.company.hrms.DataAccess.Abstracts.JobDao;
import com.company.hrms.Entities.Concretes.Job;
import com.company.hrms.Entities.Concretes.SystemUser;
import com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobManager implements JobService {

    private final JobDao jobDao;
    private final SystemUserService systemUserService;

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobs() {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobDao.findAllActiveJobAnnouncesDetails(),
                "Aktif iş ilanları listelendi");
    }

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobsBySortingDate() {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobDao.findAllActiveJobAnnouncesOrderByAnnounceDateDetails(
                        Sort.by(Sort.Direction.ASC,"announceDate")),
                "Aktif iş ilanları tarihe göre listelendi");
    }

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobDao.findByIsActiveAndEmployer_CompanyName(companyName),
                "Aktif "+companyName+" iş ilanları listelendi");
    }

    @Override
    public DataResult<Job> announceJob(Job job) throws Exception {

        validateJob(job);

        return new SuccessDataResult<Job>(
                jobDao.save(job)
                ,"İş ilanı eklendi.");
    }

    @Override
    public Result closeJobAnnounce(int id) {
        jobDao.closeJobAnnounce(id);
        return new SuccessResult("İlan kapatıldı id: "+id);
    }

    @Override
    public DataResult<Job> activateJobAnnounceBySystemUser(int jobId, int systemUserId) {
        Job job = jobDao.findById(jobId).orElseThrow(() -> new EntityNotFoundException("İş ilanı bulunamadı"));
        SystemUser systemUser = systemUserService.findSystemUserById(systemUserId);

        job.setConfirmerSystemUser(systemUser);
        job.setIsActive(true);

        return new SuccessDataResult<Job>(jobDao.save(job),
                "İş ilanı "+systemUser.getEmail()+ " tarafından onaylandı.");
    }

    public void validateJob(Job job) throws Exception {
        if(job.getMinSalary() != 0 && job.getMaxSalary() != 0 && job.getMinSalary() > job.getMaxSalary()){
            throw new ValidationException("Minimum maaş miktarı maximum maaş miktarından büyük olamaz.");
        }
    }
}
