package com.company.hrms.Core.Adapters;

import Mernis.OPAKPSPublicSoap;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Qualifier(value = "MernisIdendityAdapter")
public class MernisVerificationAdapter implements IdentityVerificationService {

    @Override
    public boolean verificationUser(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception {
        OPAKPSPublicSoap opakpsPublicSoap = new OPAKPSPublicSoap();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(jobSeekerRegisterDto.getBirthDate());

        boolean result =  opakpsPublicSoap.TCKimlikNoDogrula(Long.parseLong(jobSeekerRegisterDto.getNationalityId()), jobSeekerRegisterDto.getFirstName().toUpperCase(),
                jobSeekerRegisterDto.getLastName().toUpperCase(), calendar.get(Calendar.YEAR));
        return result;
    }
}
