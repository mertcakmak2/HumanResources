package com.company.hrms.Core.Adapters;

import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "MockMernisIdendityAdapter")
public class MockMernisVerificationAdapter implements IdentityVerificationService {

    @Override
    public boolean verificationUser(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception {
        return true;
    }
}
