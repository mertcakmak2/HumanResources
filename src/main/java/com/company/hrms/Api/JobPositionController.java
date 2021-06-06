package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobPositionService;
import com.company.hrms.Entities.Concretes.JobPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-position")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @PostMapping(value = "")
    public JobPosition saveJobPosition(@RequestBody JobPosition jobPosition) {
        return jobPositionService.saveJobPosition(jobPosition);
    }

    @GetMapping(value = "")
    public List<JobPosition> findAllJobPositions() {
        return jobPositionService.findAllJobPositions();
    }

    @DeleteMapping(value = "/{id}")
    public String deleteJobPosition(@PathVariable int id){
        return  jobPositionService.deleteJobPosition(id);
    }

}
