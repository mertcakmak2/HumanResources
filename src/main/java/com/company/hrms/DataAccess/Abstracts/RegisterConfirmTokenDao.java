package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterConfirmTokenDao extends JpaRepository<RegisterConfirmToken, Integer> {

    Optional<RegisterConfirmToken> findByToken(String token);
    Optional<RegisterConfirmToken> findByUserId(int userId);
}
