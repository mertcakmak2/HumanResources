package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.ConfirmationEmployer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationEmployerDao extends JpaRepository<ConfirmationEmployer, Integer> {
}
