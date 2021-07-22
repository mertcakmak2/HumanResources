package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobTypeService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.JobType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-type")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequiredArgsConstructor
public class JobTypeController {

    private final JobTypeService jobTypeService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<JobType>> findAllJobTypes(){
        return jobTypeService.findAllJobTypes();
    }
}
