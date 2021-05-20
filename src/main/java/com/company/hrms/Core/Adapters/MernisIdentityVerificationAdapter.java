package com.company.hrms.Core.Adapters;

import Mernis.OPAKPSPublicSoap;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Entities.Concretes.JobSeeker;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component(value = "MernisIdendityAdapter")
public class MernisIdentityVerificationAdapter implements IdentityVerificationService {

    @Override
    public boolean verificationUser(JobSeeker jobSeeker) throws Exception {
        OPAKPSPublicSoap opakpsPublicSoap = new OPAKPSPublicSoap();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(jobSeeker.getBirthDate());

        boolean result =  opakpsPublicSoap.TCKimlikNoDogrula(Long.parseLong(jobSeeker.getNationalityId()), jobSeeker.getFirstName().toUpperCase(),
                jobSeeker.getLastName().toUpperCase(), calendar.get(Calendar.YEAR));
        return result;
    }
}
