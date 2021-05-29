package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.DataAccess.Abstracts.SystemUserDao;
import com.company.hrms.Entities.Concretes.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class SystemUserManager implements SystemUserService {

    private final SystemUserDao systemUserDao;

    @Override
    public SystemUser findSystemUserById(int id) {
        return systemUserDao.findById(id).orElseThrow(() -> new EntityNotFoundException("System user bulunamadı"));
    }
}
