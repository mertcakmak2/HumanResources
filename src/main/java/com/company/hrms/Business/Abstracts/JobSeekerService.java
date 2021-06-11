package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobSeeker;
import javassist.NotFoundException;

import java.util.List;

public interface JobSeekerService {

    JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception;
    JobSeeker findJobSeekerById(int id) throws NotFoundException;
    DataResult<List<JobSeeker>> findAllJobSeekers();
    JobSeeker deleteJobSeeker(JobSeeker jobSeeker);

}
