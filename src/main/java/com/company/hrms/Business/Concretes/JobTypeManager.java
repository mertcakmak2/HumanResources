package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobTypeService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.JobTypeDao;
import com.company.hrms.Entities.Concretes.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTypeManager implements JobTypeService {

    private final JobTypeDao jobTypeDao;

    @Override
    public DataResult<List<JobType>> findAllJobTypes() {
        return new SuccessDataResult<List<JobType>>(jobTypeDao.findAll(),"İş tipleri listelendi.");
    }
}
