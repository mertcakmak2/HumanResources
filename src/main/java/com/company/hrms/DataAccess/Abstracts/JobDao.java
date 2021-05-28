package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobDao extends JpaRepository<Job, Integer> {

    List<Job> findByIsActive(boolean isActive);
    List<Job> findByIsActive(boolean isActive, Sort sort);
    List<Job> findByIsActiveAndCompanyName(boolean isActive, String companyName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Job j set j.isActive = false WHERE j.id =?1")
    void closeJobAnnounce(int id);
}
