package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerDefaultReturnDto;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;

public interface RegisterJobSeekerService {

    JobSeekerDefaultReturnDto registerJobSeeker(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception;
    String confirmWithEmail(String token);

}
