package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.WorkingConcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingConceptDao extends JpaRepository<WorkingConcept, Integer> {
}
