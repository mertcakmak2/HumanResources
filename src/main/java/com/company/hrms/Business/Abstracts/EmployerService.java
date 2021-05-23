package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Dto.Employer.EmployerRegisterDto;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface EmployerService {

    Employer saveEmployer(EmployerRegisterDto employerRegisterDto) throws Exception;
    Employer findEmployerById(int id) throws UsernameNotFoundException;
    List<Employer> findAllEmployers();
    Employer deleteEmployer(Employer employer);
}
