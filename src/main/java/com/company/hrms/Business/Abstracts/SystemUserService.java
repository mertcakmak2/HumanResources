package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.SystemUser;

import java.util.List;

public interface SystemUserService {

    DataResult<List<SystemUser>> findAllSystemUsers();
    DataResult<SystemUser> updateSystemUser(SystemUser systemUser);
    SystemUser findSystemUserById(int id);
}
