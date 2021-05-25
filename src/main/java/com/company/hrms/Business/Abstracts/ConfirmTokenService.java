package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import com.company.hrms.Entities.Concretes.User;

import java.util.Optional;

public interface ConfirmTokenService {

    RegisterConfirmToken saveMailConfirmToken(User user);

    RegisterConfirmToken confirmMailToken(String confirmToken);

    Optional<RegisterConfirmToken> findByToken(String confirmToken);

    RegisterConfirmToken findByUserId(int userId);

}
