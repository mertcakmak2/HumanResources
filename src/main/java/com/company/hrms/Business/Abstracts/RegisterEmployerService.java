package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Dto.Employer.EmployerRegisterDto;

public interface RegisterEmployerService {

    Employer registerEmployer(EmployerRegisterDto employerRegisterDto) throws Exception;
    String confirmWithEmail(String token);
    String confirmWithSystemUser(int employerId, int systemUserId);
}
