package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Concretes.School;

import java.util.List;

public interface SchoolService {

    DataResult<School> saveSchool(School school);
    DataResult<School> updateSchool(School school);
}
