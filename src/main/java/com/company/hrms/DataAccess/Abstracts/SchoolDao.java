package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {
    List<School> findByResume_IdAndIsActive(int resumeId, boolean active);
}
