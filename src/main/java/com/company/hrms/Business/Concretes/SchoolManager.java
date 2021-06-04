package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.SchoolService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.SchoolDao;
import com.company.hrms.Entities.Concretes.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolManager implements SchoolService {

    private final SchoolDao schoolDao;

    @Override
    public DataResult<School> saveSchool(School school) {
        return new SuccessDataResult<School>(schoolDao.save(school), "Okul kaydedildi.");
    }
}
