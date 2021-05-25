package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.SystemUser;

public interface SystemUserService {

    SystemUser findSystemUserById(int id);
}
