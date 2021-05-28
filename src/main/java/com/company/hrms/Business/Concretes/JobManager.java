package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Result.SuccessResult;
import com.company.hrms.DataAccess.Abstracts.JobDao;
import com.company.hrms.Entities.Concretes.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobManager implements JobService {

    private final JobDao jobDao;

    @Override
    public DataResult<List<Job>> findAllActiveJobs() {
        return new SuccessDataResult<List<Job>>(
                jobDao.findByIsActive(true),
                "Aktif iş ilanları listelendi");
    }

    @Override
    public DataResult<List<Job>> findAllActiveJobsBySortingDate() {
        return new SuccessDataResult<List<Job>>(
                jobDao.findByIsActive(true, Sort.by(Sort.Direction.ASC,"announceDate")),
                "Aktif iş ilanları tarihe göre listelendi");
    }

    @Override
    public DataResult<List<Job>> findAllActiveJobByCompanyName(String companyName) {
        return new SuccessDataResult<List<Job>>(
                jobDao.findByIsActiveAndCompanyName(true, companyName),
                "Aktif "+companyName+" iş ilanları listelendi");
    }

    @Override
    public DataResult<Job> announceJob(Job job) {
        return new SuccessDataResult<Job>(
                jobDao.save(job)
                ,"İş ilanı eklendi.");
    }

    @Override
    public Result closeJobAnnounce(int id) {
        jobDao.closeJobAnnounce(id);
        return new SuccessResult("İlan kapatıldı id: "+id);
    }
}