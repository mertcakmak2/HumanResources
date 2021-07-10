package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.JobAnnounce;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto;
import com.company.hrms.Entities.Dtos.JobAnnounce.JobAnnounceFilterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobAnnounceDao extends JpaRepository<JobAnnounce, Integer> {

    // Tüm Job fieldlarını döner..
    List<JobAnnounce> findByIsActive(boolean isActive);
    // Tüm Job fieldlarını döner..
    List<JobAnnounce> findByIsActive(boolean isActive, Sort sort);
    // Tüm Job fieldlarını döner..
    List<JobAnnounce> findByIsActiveAndEmployer_CompanyName(boolean isActive, String companyName);

    //Req 8 => Sistemdeki tüm aktif iş ilanları listelenebilmelidir. DTO'lu
    @Query("Select new com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, j.jobDescription, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From JobAnnounce j Join j.employer e Join j.jobPosition p where j.isActive=true ")
    List<JobActiveAnnouncesDto> findAllActiveJobAnnouncesDetails();

    //Req 9 => Sistemdeki tüm aktif iş ilanları tarihe göre listelenebilmelidir. DTO'lu
    @Query("Select new com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, j.jobDescription, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From JobAnnounce j Join j.employer e Join j.jobPosition p where j.isActive=true")
    List<JobActiveAnnouncesDto> findAllActiveJobAnnouncesOrderByAnnounceDateDetails(Sort sort);

    //Req 10 : Sistemde bir firmaya ait tüm aktif iş ilanları listelenebilmelidir.
    @Query("Select new com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, j.jobDescription, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From JobAnnounce j Join j.employer e Join j.jobPosition p where j.isActive=true and e.companyName=?1")
    List<JobActiveAnnouncesDto> findByIsActiveAndEmployer_CompanyName(String companyName);

    //Req 22: Adaylar ilanları lokasyon, çalışma türü (yarı zamanlı vb) seçeneklerine göre filtreleyebilmelidir.
    @Query("Select new com.company.hrms.Entities.Dtos.JobAnnounce.JobActiveAnnouncesDto"
            + "(j.id, e.companyName, j.jobDescription, p.positionName ,j.positionCount, j.announceDate, j.lastDateOfAppeal) "
            + "From JobAnnounce j Join j.employer e Join j.jobPosition p " +
            "where j.isActive=true " +
            "and ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"+
            "and ((:#{#filter.jobTypeId}) IS NULL OR j.jobType.id IN (:#{#filter.jobTypeId})) ")
    List<JobActiveAnnouncesDto> findByCityIdAndJobTypeId(Pageable pageable, @Param("filter") JobAnnounceFilterDto filter);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update JobAnnounce j set j.isActive = false WHERE j.id =?1")
    void closeJobAnnounce(int id);

}
