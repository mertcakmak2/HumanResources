package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Entities.Concretes.JobSeeker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-seekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    @GetMapping(value = "")
    public List<JobSeeker> findAllJobSeekers(){
        return jobSeekerService.findAllJobSeekers();
    }


}
