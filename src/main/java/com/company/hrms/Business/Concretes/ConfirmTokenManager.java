package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.ConfirmTokenService;
import com.company.hrms.DataAccess.Abstracts.RegisterConfirmTokenDao;
import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import com.company.hrms.Core.Entitites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmTokenManager implements ConfirmTokenService {

    private final RegisterConfirmTokenDao registerConfirmTokenDao;

    @Override
    public RegisterConfirmToken saveMailConfirmToken(User user) {
        RegisterConfirmToken confirmToken = new RegisterConfirmToken(
                UUID.randomUUID().toString(), LocalDateTime.now().plusMinutes(30L), user);
        return registerConfirmTokenDao.save(confirmToken);
    }

    @Override
    public RegisterConfirmToken confirmMailToken(String confirmToken) {

        RegisterConfirmToken confirmationToken = findByToken(confirmToken)
                .orElseThrow(() -> new IllegalStateException("Doğrulama kodu bulunamadı."));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Bu email zaten doğrulanmış.");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Hesap doğrulama süresi aşılmıştır.");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        return registerConfirmTokenDao.save(confirmationToken);
    }

    @Override
    public Optional<RegisterConfirmToken> findByToken(String confirmToken) {
        return registerConfirmTokenDao.findByToken(confirmToken);
    }

    @Override
    public RegisterConfirmToken findByUserId(int userId) {
        return registerConfirmTokenDao.findByUserId(userId).orElse(null);
    }

}
