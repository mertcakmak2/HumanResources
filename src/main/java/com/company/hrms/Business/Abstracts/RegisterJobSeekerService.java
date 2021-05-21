package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Dto.JobSeekerDto;

public interface RegisterJobSeekerService {

    JobSeekerDto registerJobSeeker(JobSeeker jobSeeker) throws Exception;
    String confirmWithEmail(String token);

}
