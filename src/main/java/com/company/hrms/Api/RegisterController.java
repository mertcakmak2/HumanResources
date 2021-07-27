package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ConfirmTokenService;
import com.company.hrms.Business.Abstracts.RegisterEmployerService;
import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RegisterController {

    private final RegisterJobSeekerService registerJobSeekerService;
    private final RegisterEmployerService registerEmployerService;

    @PostMapping(value = "/job-seeker")
    public JobSeeker registerJobSeeker(@Valid @RequestBody JobSeeker jobSeeker) throws Exception {
        return registerJobSeekerService.registerJobSeeker(jobSeeker);
    }

    @PostMapping(value = "/employer")
    public Employer registerEmployer(@Valid @RequestBody Employer employer) throws Exception {
        return registerEmployerService.registerEmployer(employer);
    }

    @PostMapping(value = "/job-seeker/confirm")
    public String confirmJobSeekerTokenWithEmail(@RequestBody RegisterConfirmToken token) {
        return registerJobSeekerService.confirmJobSeekerTokenWithEmail(token.getToken());
    }

    @PostMapping(value = "/employer/confirm")
    public String confirmEmployerTokenWithEmail(@RequestBody RegisterConfirmToken token) throws Exception {
        return registerEmployerService.confirmEmployerTokenWithEmail(token.getToken());
    }

    @GetMapping(value = "/employer/{employerId}/{systemUserId}")
    public String confirmEmployerWithSystemUser(@PathVariable int employerId, @PathVariable int systemUserId) throws Exception {
        return registerEmployerService.confirmEmployerWithSystemUser(employerId, systemUserId);
    }

    @ExceptionHandler(value = {
            EntityExistsException.class,
            ValidationException.class,
            IllegalStateException.class
    })
    public ResponseEntity handleException(Exception e, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(400).body("Exception Message Found: "+e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        ValidationExceptionHandler validationExceptionHandler = new ValidationExceptionHandler();
        return validationExceptionHandler.getValidateExceptions(exceptions);
    }

}
