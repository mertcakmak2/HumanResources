package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerDefaultReturnDto;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/register/job-seeker")
@RequiredArgsConstructor
public class RegisterJobSeekerController {

    private final RegisterJobSeekerService registerJobSeekerService;

    @PostMapping(value = "")
    public JobSeekerDefaultReturnDto registerJobSeeker(@RequestBody JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception {
        return registerJobSeekerService.registerJobSeeker(jobSeekerRegisterDto);
    }

    @GetMapping(value = "/confirm")
    public String confirmWithEmail(@RequestParam String token) {
        return registerJobSeekerService.confirmWithEmail(token);
    }

    @ExceptionHandler(value = {
            UsernameNotFoundException.class,
            IllegalStateException.class,
            EntityExistsException.class
    })
    public ResponseEntity handleException(RuntimeException e, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(404).body("Exception Message Found: "+e.getMessage());
    }

}
