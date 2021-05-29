package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ConfirmTokenService;
import com.company.hrms.Business.Abstracts.RegisterEmployerService;
import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.JobSeeker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterJobSeekerService registerJobSeekerService;
    private final RegisterEmployerService registerEmployerService;
    private final ConfirmTokenService confirmTokenService;

    @PostMapping(value = "/job-seeker")
    public JobSeeker registerJobSeeker(@RequestBody JobSeeker jobSeeker) throws Exception {
        return registerJobSeekerService.registerJobSeeker(jobSeeker);
    }

    @PostMapping(value = "/employer")
    public Employer registerEmployer(@RequestBody Employer employer) throws Exception {
        return registerEmployerService.registerEmployer(employer);
    }

    @GetMapping(value = "/job-seeker/confirm")
    public String confirmJobSeekerTokenWithEmail(@RequestParam String token) {
        return registerJobSeekerService.confirmJobSeekerTokenWithEmail(token);
    }

    @GetMapping(value = "/employer/confirm")
    public String confirmEmployerTokenWithEmail(@RequestParam String token) throws Exception {
        return registerEmployerService.confirmEmployerTokenWithEmail(token);
    }

    @GetMapping(value = "/employer/{employerId}/{systemUserId}")
    public String confirmEmployerWithSystemUser(@PathVariable int employerId, @PathVariable int systemUserId) throws Exception {
        return registerEmployerService.confirmEmployerWithSystemUser(employerId, systemUserId);
    }

    @ExceptionHandler(value = {
            IllegalStateException.class,
            EntityExistsException.class,
            ValidationException.class,
    })
    public ResponseEntity handleException(Exception e, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(404).body("Exception Message Found: "+e.getMessage());
    }

}
