package com.company.hrms.Core;

import com.company.hrms.Entities.Concretes.JobSeeker;
import org.springframework.stereotype.Component;

@Component
public interface IdentityVerificationService {

    boolean verificationUser(JobSeeker jobSeeker) throws Exception;
}
