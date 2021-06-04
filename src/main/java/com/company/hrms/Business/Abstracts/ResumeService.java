package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Dtos.ResumeSaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    DataResult<List<Resume>> findAllResumes();

    Result saveResume(ResumeSaveDto resumeSaveDto);

    Result setResumeProfilPicture(MultipartFile picture, int resumeId);
}
