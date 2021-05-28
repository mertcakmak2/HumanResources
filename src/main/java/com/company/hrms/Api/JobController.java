package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping(value = "")
    public DataResult<List<Job>> findAllActiveJobs() {
        return jobService.findAllActiveJobs();
    }

    @GetMapping(value = "/findAllActiveJobsBySortingDate")
    public DataResult<List<Job>> findAllActiveJobsBySortingDate() {
        return jobService.findAllActiveJobsBySortingDate();
    }

    @GetMapping(value = "/findAllActiveJobByCompanyName")
    public DataResult<List<Job>> findAllActiveJobByCompanyName(@RequestParam String companyName) {
        return jobService.findAllActiveJobByCompanyName(companyName);
    }

    @PostMapping (value = "")
    public DataResult<Job> announceJob(@RequestBody Job job) {
        return jobService.announceJob(job);
    }

    @GetMapping(value = "/closeJobAnnounce")
    public Result closeJobAnnounce(@RequestParam int id) {
        return jobService.closeJobAnnounce(id);
    }
}
