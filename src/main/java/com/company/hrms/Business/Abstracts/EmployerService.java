package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.Employer;
import javassist.NotFoundException;

import java.util.List;

public interface EmployerService {

    Employer saveEmployer(Employer employer) throws Exception;
    Employer findEmployerById(int id) throws NotFoundException;
    List<Employer> findAllEmployers();
    Employer deleteEmployer(Employer employer);
}
