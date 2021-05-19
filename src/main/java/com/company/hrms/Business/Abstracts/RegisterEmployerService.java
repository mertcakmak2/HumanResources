package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;

public interface RegisterEmployerService {

    Employer registerEmployer(Employer employer) throws Exception;
    String confirmWithEmail(String token);
    String confirmWithSystemUser(int employerId, int systemUserId);
}
