package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.School;
import com.company.hrms.Entities.Dtos.School.SchoolSaveDto;

import java.util.List;

public interface SchoolService {

    DataResult<List<School>> findAllSchoolByResumeId(int resumeId);
    DataResult<School> saveSchool(SchoolSaveDto schoolSaveDto);
    DataResult<School> updateSchool(School school);
    DataResult<School> deleteSchool(int schoolId);
}
