package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import com.company.hrms.Entities.Dtos.Resume.ResumeSaveDto;
import com.company.hrms.Entities.Dtos.Resume.ResumeUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface ResumeService {

    DataResult<Resume> findByJobSeekerId(int jobSeekerId);

    DataResult<List<Resume>> findAllResumes();

    Result saveResume(ResumeSaveDto resumeSaveDto);

    DataResult<Resume> updateResume(ResumeUpdateDto resumeUpdateDto);

}
