package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobPositionService;
import com.company.hrms.Entities.Concretes.JobPosition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-position")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @PostMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public JobPosition saveJobPosition(@RequestBody JobPosition jobPosition) {
        return jobPositionService.saveJobPosition(jobPosition);
    }

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public List<JobPosition> findAllJobPositions() {
        return jobPositionService.findAllJobPositions();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public String deleteJobPosition(@PathVariable int id){
        return  jobPositionService.deleteJobPosition(id);
    }

}
