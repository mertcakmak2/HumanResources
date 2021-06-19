package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Job;
import com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto;
import org.springframework.data.domain.Sort;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface JobService {

    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobs();
    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobsBySortingDate();
    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobByCompanyName(String companyName);
    DataResult<Job> announceJob(Job job) throws Exception;
    Result closeJobAnnounce(int id);
    DataResult<Job> activateJobAnnounceBySystemUser(int jobId, int systemUserId);

}
