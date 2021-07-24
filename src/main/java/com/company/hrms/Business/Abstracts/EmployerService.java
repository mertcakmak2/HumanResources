package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.DataAccess.Abstracts.EmployerUpdateRequestDao;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import com.company.hrms.Entities.Dtos.Employer.EmployerCompanyUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface EmployerService {

    Employer saveEmployer(Employer employer) throws Exception;
    DataResult<EmployerUpdateRequest> approveForUpdateEmployerCompany(EmployerCompanyUpdateDto employerCompanyUpdateDto, int employerId) throws Exception;
    Employer findEmployerById(int id) throws NotFoundException;
    DataResult<Employer> findEmployerByEmail(String email) throws NotFoundException;
    DataResult<List<Employer>> findAllEmployers();
    DataResult<Employer> updateEmployerCompany(int systemUserId, int employerId) throws NotFoundException;

}
