package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.JobSeeker;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobSeekerService {

    JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception;
    JobSeeker findJobSeekerById(int id) throws NotFoundException;
    DataResult<JobSeeker> findJobSeekerByEmail(String email) throws NotFoundException;
    DataResult<List<JobSeeker>> findAllJobSeekers();
    JobSeeker deleteJobSeeker(JobSeeker jobSeeker);
    DataResult<JobSeeker> saveJobSeekerProfilePic(MultipartFile picture, int jobSeekerId) throws NotFoundException;

}
