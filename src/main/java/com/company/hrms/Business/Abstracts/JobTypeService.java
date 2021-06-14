package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.DataAccess.Abstracts.JobTypeDao;
import com.company.hrms.Entities.Concretes.JobType;

import java.util.List;

public interface JobTypeService {

    DataResult<List<JobType>> findAllJobTypes();
}
