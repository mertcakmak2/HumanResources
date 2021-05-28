package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Job;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface JobService {

    DataResult<List<Job>> findAllActiveJobs();
    DataResult<List<Job>> findAllActiveJobsBySortingDate();
    DataResult<List<Job>> findAllActiveJobByCompanyName(String companyName);
    DataResult<Job> announceJob(Job job) throws Exception;
    Result closeJobAnnounce(int id);

}
