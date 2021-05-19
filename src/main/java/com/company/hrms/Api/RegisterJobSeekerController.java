package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Entities.Concretes.JobSeeker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register/job-seeker")
@RequiredArgsConstructor
public class RegisterJobSeekerController {

    private final RegisterJobSeekerService registerJobSeekerService;

    @PostMapping(value = "")
    public JobSeeker registerJobSeeker(@RequestBody JobSeeker jobSeeker) throws Exception {
        return registerJobSeekerService.registerJobSeeker(jobSeeker);
    }

    @GetMapping(value = "/confirm")
    public String confirmWithEmail(@RequestParam String token) {
        return registerJobSeekerService.confirmWithEmail(token);
    }
}
