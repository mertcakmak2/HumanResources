package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import javassist.NotFoundException;

public interface RegisterEmployerService {

    Employer registerEmployer(Employer employer) throws Exception;
    String confirmEmployerTokenWithEmail(String token) throws NotFoundException, Exception;
    String confirmEmployerWithSystemUser(int employerId, int systemUserId) throws NotFoundException, Exception;
}
