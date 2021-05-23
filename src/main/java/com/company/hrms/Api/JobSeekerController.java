package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Entities.Concretes.JobSeeker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public List<JobSeeker> findAllEmployers(){
        return jobSeekerService.findAllJobSeekers();
    }


}
