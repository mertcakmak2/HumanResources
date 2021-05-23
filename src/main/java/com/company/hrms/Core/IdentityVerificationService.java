package com.company.hrms.Core;

import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;
import org.springframework.stereotype.Component;

@Component
public interface IdentityVerificationService {

    boolean verificationUser(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception;
}
