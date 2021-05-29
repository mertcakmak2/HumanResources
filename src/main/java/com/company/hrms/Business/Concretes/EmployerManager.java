package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Core.Utilities.Util;
import com.company.hrms.DataAccess.Abstracts.EmployerDao;
import com.company.hrms.Entities.Concretes.Employer;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;

    @Override
    public Employer saveEmployer(Employer employer) throws Exception {

        validateEmployer(employer);

        return employerDao.save(employer);
    }

    @Override
    public Employer findEmployerById(int id) throws NotFoundException {
        return employerDao.findById(id).orElseThrow(() -> new NotFoundException("İş veren bulunamadı."));
    }

    @Override
    public List<Employer> findAllEmployers() {
        return employerDao.findAll();
    }

    @Override
    public Employer deleteEmployer(Employer employer) {
        employerDao.delete(employer);
        return employer;
    }

    public void validateEmployer(Employer employer) throws Exception {
        // Field controls
        if (employer.getEmail() == null || employer.getFirstName() == null || employer.getLastName() == null ||
            employer.getCompanyName() == null || employer.getCompanyWebSite() == null || employer.getMobilePhone() == null) {
            throw new ValidationException("Tüm alanları doldurduğunuzdan emin olunuz");
        }
        // Email Regex
        if (!Util.checkUserEmail(employer.getEmail()))
            throw new ValidationException("Lütfen geçerli bir email adresi giriniz");

        if(employerDao.existsByEmail(employer.getEmail()))
            throw new EntityExistsException("Bu email adresi kullanımda");

        //Domain Control
        String domain = employer.getEmail().substring(employer.getEmail().indexOf("@") + 1);
        if(!domain.contains(employer.getCompanyWebSite()))
            throw new ValidationException("Lütfen web siteniz ile aynı domaine sahip bir mail adresi giriniz");
    }
}
