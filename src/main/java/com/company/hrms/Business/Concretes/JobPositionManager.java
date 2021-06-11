package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobPositionService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.JobPositionDao;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.JobPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
@RequiredArgsConstructor
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @CachePut(cacheNames = "jobPosition", key = "'jobPosition#' + #jobPosition.id")
    @CacheEvict(cacheNames = "job_position_cache", allEntries = true)
    @Override
    public DataResult<JobPosition> saveJobPosition(JobPosition jobPosition) {
        return new SuccessDataResult<JobPosition>(
                jobPositionDao.save(jobPosition),"İş pozisyonu kaydedildi");
    }

    @Override
    @Cacheable(cacheNames = "job_position_cache")
    public DataResult<List<JobPosition>> findAllJobPositions() {
        List<JobPosition> positions = jobPositionDao.findAll();
        return new SuccessDataResult<List<JobPosition>>(positions,"İş pozisyonlar listelendi.");
    }

    @CacheEvict(cacheNames = "job_position_cache", allEntries = true)
    @Override
    public String deleteJobPosition(int id) {
        jobPositionDao.deleteById(id);
        return "İş pozisyonu başaraıyla silindi.";
    }
}
