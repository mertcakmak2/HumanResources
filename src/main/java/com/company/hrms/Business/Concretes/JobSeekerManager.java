package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.DataAccess.Abstracts.JobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.User;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final UserDao userDao;

    @Override
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception {

        // TODO: kodlama.io business validation ( mernis, regex, field controls..)
        validateJobSeeker(jobSeeker);

        User user = userDao.save(new User(jobSeeker.getEmail(), jobSeeker.getPassword()));
        jobSeeker.setUser(user);

        return jobSeekerDao.save(jobSeeker);
    }

    @Override
    public JobSeeker findJobSeekerById(int id) throws NotFoundException {
        return jobSeekerDao.findById(id).orElseThrow(() -> new NotFoundException("not found job seeker"));
    }

    @Override
    public List<JobSeeker> findAllJobSeekers() {
        return jobSeekerDao.findAll();
    }

    @Override
    public JobSeeker deleteJobSeeker(JobSeeker jobSeeker) {
        jobSeekerDao.delete(jobSeeker);
        return jobSeeker;
    }

    @Override
    public boolean validateJobSeeker(JobSeeker jobSeeker) throws Exception {
        return true;
    }
}
