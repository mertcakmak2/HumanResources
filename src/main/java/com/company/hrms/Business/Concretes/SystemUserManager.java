package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.SystemUserDao;
import com.company.hrms.Entities.Concretes.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemUserManager implements SystemUserService {

    private final SystemUserDao systemUserDao;

    @Override
    public DataResult<List<SystemUser>> findAllSystemUsers() {
        return new SuccessDataResult<List<SystemUser>>(systemUserDao.findAll(), "Tüm sistem personelleri listelendi");
    }

    @Override
    public DataResult<SystemUser> updateSystemUser(SystemUser systemUser) {
        SystemUser existSystemUser = findSystemUserById(systemUser.getId());

        existSystemUser = systemUser;
        return new SuccessDataResult<SystemUser>(systemUserDao.save(existSystemUser),"Sistem personeli güncellendi");
    }

    @Override
    public SystemUser findSystemUserById(int id) {
        return systemUserDao.findById(id).orElseThrow(() -> new EntityNotFoundException("System user bulunamadı"));
    }
}
