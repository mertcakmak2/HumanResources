package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.JobSeeker;

public interface RegisterJobSeekerService {

    JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws Exception;
    String confirmWithEmail(String token);

}
