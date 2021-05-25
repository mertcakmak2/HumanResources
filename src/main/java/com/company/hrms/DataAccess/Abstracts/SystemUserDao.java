package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {

}
