package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.DataAccess.Abstracts.EmployerDao;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.User;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final UserDao userDao;

    @Override
    public Employer saveEmployer(Employer employer) throws Exception {

        // TODO: validate işlemleri yapılacak

        User user = userDao.save(new User(employer.getCompanyEmail(), employer.getPassword()));
        employer.setUser(user);

        return employerDao.save(employer);
    }

    @Override
    public Employer findEmployerById(int id) throws NotFoundException {
        return employerDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("employer not found"));
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

    @Override
    public boolean validateEmployer(Employer employer) throws Exception {
        return true;
    }
}
