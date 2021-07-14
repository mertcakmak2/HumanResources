package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobAnnounceService;
import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.Core.Utilities.Result.*;
import com.company.hrms.DataAccess.Abstracts.JobAnnounceDao;
import com.company.hrms.Entities.Concretes.JobAnnounce;
import com.company.hrms.Entities.Concretes.SystemUser;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobAnnounceFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobAnnounceManager implements JobAnnounceService {

    private final JobAnnounceDao jobAnnounceDao;
    private final SystemUserService systemUserService;

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobs() {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobAnnounceDao.findAllActiveJobAnnouncesDetails(),
                "Aktif iş ilanları listelendi");
    }

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobsBySortingDate() {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobAnnounceDao.findAllActiveJobAnnouncesOrderByAnnounceDateDetails(
                        Sort.by(Sort.Direction.ASC,"announceDate")),
                "Aktif iş ilanları tarihe göre listelendi");
    }

    @Override
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobAnnounceDao.findByIsActiveAndEmployer_CompanyName(companyName),
                "Aktif "+companyName+" iş ilanları listelendi");
    }

    @Override
    public DataResult<JobAnnounce> announceJob(JobAnnounce jobAnnounce) throws Exception {

        validateJob(jobAnnounce);

        return new SuccessDataResult<JobAnnounce>(
                jobAnnounceDao.save(jobAnnounce)
                ,"İş ilanı eklendi.");
    }

    @Override
    public Result closeJobAnnounce(int id) {
        jobAnnounceDao.closeJobAnnounce(id);
        return new SuccessResult("İlan kapatıldı id: "+id);
    }

    @Override
    public DataResult<JobAnnounce> activateJobAnnounceBySystemUser(int jobId, int systemUserId) {
        JobAnnounce jobAnnounce = jobAnnounceDao.findById(jobId).orElseThrow(() -> new EntityNotFoundException("İş ilanı bulunamadı"));
        SystemUser systemUser = systemUserService.findSystemUserById(systemUserId);

        jobAnnounce.setConfirmerSystemUser(systemUser);
        jobAnnounce.setIsActive(true);

        return new SuccessDataResult<JobAnnounce>(jobAnnounceDao.save(jobAnnounce),
                "İş ilanı "+systemUser.getEmail()+ " tarafından onaylandı.");
    }

    @Override
    public PaginationDataResult<List<JobActiveAnnouncesDto>> findByCityIdAndJobTypeId(JobAnnounceFilterDto filter) {
        Pageable PageAndSortingByAnnounceDate = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                Sort.by(Sort.Direction.ASC,"announceDate"));

        List<JobActiveAnnouncesDto> jobAnnounces = jobAnnounceDao.findByCityIdAndJobTypeId(
                PageAndSortingByAnnounceDate,
                filter
        );
        int totalData = jobAnnounceDao.findByCityIdAndJobTypeId(filter);

        return new PaginationDataResult<List<JobActiveAnnouncesDto>>(
                jobAnnounces,
                "İş ilanları şehir ve çalışma tipine göre filtrelendi.",
                totalData
        );
        /*return new SuccessDataResult<List<JobActiveAnnouncesDto>>(
                jobAnnounces,
                "İş ilanları şehir ve çalışma tipine göre filtrelendi."
        );*/
    }

    public void validateJob(JobAnnounce jobAnnounce) throws Exception {
        if(jobAnnounce.getMinSalary() != 0 && jobAnnounce.getMaxSalary() != 0 && jobAnnounce.getMinSalary() > jobAnnounce.getMaxSalary()){
            throw new ValidationException("Minimum maaş miktarı maximum maaş miktarından büyük olamaz.");
        }
    }
}
