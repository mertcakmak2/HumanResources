package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.PaginationDataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.JobAnnounce;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobAnnounceFilterDto;

import java.util.List;

public interface JobAnnounceService {

    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobs();
    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobsBySortingDate();
    DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobByCompanyName(String companyName);
    DataResult<JobAnnounce> announceJob(JobAnnounce jobAnnounce) throws Exception;
    Result closeJobAnnounce(int id);
    DataResult<JobAnnounce> activateJobAnnounceBySystemUser(int jobId, int systemUserId);
    PaginationDataResult<List<JobActiveAnnouncesDto>> findByCityIdAndJobTypeId(JobAnnounceFilterDto jobAnnounceFilterDto);
}
