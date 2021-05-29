package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobPositionService;
import com.company.hrms.DataAccess.Abstracts.JobPositionDao;
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
    public JobPosition saveJobPosition(JobPosition jobPosition) {
        return jobPositionDao.save(jobPosition);
    }

    @Override
    @Cacheable(cacheNames = "job_position_cache")
    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAll();
    }

    @CacheEvict(cacheNames = "job_position_cache", allEntries = true)
    @Override
    public String deleteJobPosition(int id) {
        jobPositionDao.deleteById(id);
        return "İş pozisyonu başaraıyla silindi.";
    }
}
