package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Job;
import com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobDao extends JpaRepository<Job, Integer> {

    // Tüm Job fieldlarını döner..
    List<Job> findByIsActive(boolean isActive);
    // Tüm Job fieldlarını döner..
    List<Job> findByIsActive(boolean isActive, Sort sort);
    // Tüm Job fieldlarını döner..
    List<Job> findByIsActiveAndEmployer_CompanyName(boolean isActive, String companyName);

    //Req 8 => Sistemdeki tüm aktif iş ilanları listelenebilmelidir. DTO'lu
    @Query("Select new com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From Job j Join j.employer e Join j.jobPosition p where j.isActive=true ")
    List<JobActiveAnnouncesDto> findAllActiveJobAnnouncesDetails();

    //Req 9 => Sistemdeki tüm aktif iş ilanları tarihe göre listelenebilmelidir. DTO'lu
    @Query("Select new com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From Job j Join j.employer e Join j.jobPosition p where j.isActive=true")
    List<JobActiveAnnouncesDto> findAllActiveJobAnnouncesOrderByAnnounceDateDetails(Sort sort);

    //Req 10 : Sistemde bir firmaya ait tüm aktif iş ilanları listelenebilmelidir.
    @Query("Select new com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From Job j Join j.employer e Join j.jobPosition p where j.isActive=true and e.companyName=?1")
    List<JobActiveAnnouncesDto> findByIsActiveAndEmployer_CompanyName(String companyName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Job j set j.isActive = false WHERE j.id =?1")
    void closeJobAnnounce(int id);

}
