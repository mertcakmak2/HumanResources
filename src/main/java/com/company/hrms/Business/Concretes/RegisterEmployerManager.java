package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Business.Abstracts.RegisterEmployerService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationEmployerDao;
import com.company.hrms.DataAccess.Abstracts.ConfirmationJobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.RegisterConfirmTokenDao;
import com.company.hrms.Entities.Concretes.ConfirmationEmployer;
import com.company.hrms.Entities.Concretes.ConfirmationJobSeeker;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import com.company.hrms.Entities.Dto.Employer.EmployerRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterEmployerManager implements RegisterEmployerService {

    private final EmployerService employerService;
    private final RegisterConfirmTokenDao registerConfirmTokenDao;
    private final ConfirmationEmployerDao confirmationEmployerDao;

    @Override
    public Employer registerEmployer(EmployerRegisterDto employerRegisterDto) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        employerRegisterDto.setPassword(bCryptPasswordEncoder.encode(employerRegisterDto.getPassword()));

        Employer savedEmployer = employerService.saveEmployer(employerRegisterDto);
        RegisterConfirmToken registerConfirmToken = registerConfirmTokenDao.save(new RegisterConfirmToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30L),
                savedEmployer.getUser()
        ));

        ConfirmationEmployer confirmationEmployer= new ConfirmationEmployer(registerConfirmToken, savedEmployer);
        confirmationEmployerDao.save(confirmationEmployer);

        // Todo: Send Confirmation Mail
        // Todo: mailLink => http://localhost:5002/api/register/employer/confirm?token=894b3e96-7a9b-40aa-8a25-b039f74e27c2

        return savedEmployer;
    }

    @Override
    public String confirmWithEmail(String token) {
        return null;
    }

    @Override
    public String confirmWithSystemUser(int employerId, int systemUserId) {
        return null;
    }
}
