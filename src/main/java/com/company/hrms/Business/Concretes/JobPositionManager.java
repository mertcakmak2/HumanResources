package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobPositionService;
import com.company.hrms.DataAccess.Abstracts.JobPositionDao;
import com.company.hrms.Entities.Concretes.JobPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @Override
    public JobPosition saveJobPosition(JobPosition jobPosition) {

        // TODO: Set SystemUser to jobPosition

        return jobPositionDao.save(jobPosition);
    }

    @Override
    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAll();
    }
}
