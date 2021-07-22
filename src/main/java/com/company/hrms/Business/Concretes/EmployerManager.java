package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Util;
import com.company.hrms.DataAccess.Abstracts.EmployerDao;
import com.company.hrms.DataAccess.Abstracts.EmployerUpdateRequestDao;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import com.company.hrms.Entities.Concretes.SystemUser;
import com.company.hrms.Entities.Dtos.Employer.EmployerCompanyUpdateDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final EmployerUpdateRequestDao employerUpdateRequestDao;
    private final SystemUserService systemUserService;

    @Override
    public Employer saveEmployer(Employer employer) throws Exception {

        checkUserEmail(employer.getEmail());
        existsEmailCheck(employer.getEmail());
        domainCheck(employer.getEmail(), employer.getCompanyWebSite());

        return employerDao.save(employer);
    }

    @Override
    public DataResult<EmployerUpdateRequest> approveForUpdateEmployerCompany(EmployerCompanyUpdateDto employerCompanyUpdateDto, int employerId) throws Exception {

        domainCheck(employerCompanyUpdateDto.getCompanyEmail(), employerCompanyUpdateDto.getCompanyWebSite());

        EmployerUpdateRequest existUpdateRequest =
                employerUpdateRequestDao.findByEmployer_IdAndIsConfirmed(employerId, false);
        if(existUpdateRequest != null){
            existUpdateRequest.setEmployerCompanyUpdateDto(employerCompanyUpdateDto);
            return new SuccessDataResult<EmployerUpdateRequest>(employerUpdateRequestDao.save(existUpdateRequest),
                    "Güncelleme tablosundaki kayıt yenilendi.");
        }

        Employer employer = findEmployerById(employerId);
        EmployerUpdateRequest employerUpdateRequest = new EmployerUpdateRequest(employerCompanyUpdateDto, employer);

        return new SuccessDataResult<EmployerUpdateRequest>(employerUpdateRequestDao.save(employerUpdateRequest),
                "Güncelleme tablosuna kayıt eklendi.");
    }

    @Override
    public Employer findEmployerById(int id) throws NotFoundException {
        return employerDao.findById(id).orElseThrow(() -> new NotFoundException("İş veren bulunamadı."));
    }

    @Override
    public DataResult<List<Employer>> findAllEmployers() {
        return new SuccessDataResult<List<Employer>>(employerDao.findAll(), "Tüm iş verenler listelendi");
    }

    @Override
    public DataResult<Employer> updateEmployerCompany(int systemUserId, int employerId) throws NotFoundException {

        SystemUser systemUser = systemUserService.findSystemUserById(systemUserId);
        Employer employer = findEmployerById(employerId);

        EmployerUpdateRequest updateRequest = confirmUpdateRequest(systemUser, employerId);

        employer.setCompanyName(updateRequest.getEmployerCompanyUpdateDto().getCompanyName());
        employer.setCompanyWebSite(updateRequest.getEmployerCompanyUpdateDto().getCompanyWebSite());
        employer.setEmail(updateRequest.getEmployerCompanyUpdateDto().getCompanyEmail());

        return new SuccessDataResult<Employer>(employerDao.save(employer), "Güncelleme isteği onaylandı.");
    }

    public EmployerUpdateRequest confirmUpdateRequest(SystemUser systemUser, int employerId){
        EmployerUpdateRequest updateRequest = employerUpdateRequestDao.findByEmployer_Id(employerId);
        updateRequest.setConfirmDate(new Date());
        updateRequest.setIsConfirmed(true);
        updateRequest.setSystemUser(systemUser);
        return employerUpdateRequestDao.save(updateRequest);
    }

    public boolean checkUserEmail(String email) throws ValidationException {
        // Email Regex
        if (!Util.checkUserEmail(email)) throw new ValidationException("Lütfen geçerli bir email adresi giriniz");
        return true; //Exception fırlatılmazsa true döndürür
    }

    public boolean existsEmailCheck(String email) {
        if (employerDao.existsByEmail(email)) throw new EntityExistsException("Bu email adresi kullanımda");
        return true;//Exception fırlatılmazsa true döndürür
    }

    public boolean domainCheck(String companyEmail, String companyWebSite) throws ValidationException {
        String companyWebSiteDomain = companyWebSite.split("\\.")[1];
        String companyEmailDomain = companyEmail.substring(companyEmail.indexOf("@") + 1);
        if (!companyEmailDomain.contains(companyWebSiteDomain)) throw new ValidationException("Lütfen web siteniz ile aynı domaine sahip bir mail adresi giriniz");
        return true;//Exception fırlatılmazsa true döndürür
    }
}
