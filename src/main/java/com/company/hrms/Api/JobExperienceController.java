package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobExperienceService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.JobExperience;
import com.company.hrms.Entities.Dtos.JobExperience.JobExperienceSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job-experience")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JobExperienceController {

    private final JobExperienceService jobExperienceService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<JobExperience> saveJobExperience(@Valid @RequestBody JobExperienceSaveDto jobExperienceSaveDto){
        return jobExperienceService.saveJobExperience(jobExperienceSaveDto);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<JobExperience> updateJobExperience(@Valid @RequestBody JobExperience jobExperience){
        return jobExperienceService.updateJobExperience(jobExperience);
    }

    @GetMapping(value = "/{resumeId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<JobExperience>> findAllJobExperiencesByResume_Id(@PathVariable int resumeId){
        return jobExperienceService.findAllJobExperiencesByResume_Id(resumeId);
    }

    @DeleteMapping(value = "/{jobExperienceId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<JobExperience> deleteJobExperience(@PathVariable int jobExperienceId){
        return jobExperienceService.deleteJobExperience(jobExperienceId);
    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
    })
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        ValidationExceptionHandler validationExceptionHandler = new ValidationExceptionHandler();
        return validationExceptionHandler.getValidateExceptions(exceptions);
    }
}
