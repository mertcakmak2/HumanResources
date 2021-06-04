package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobExperienceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Entities.Concretes.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job-experience")
@RequiredArgsConstructor
public class JobExperienceController {

    private final JobExperienceService jobExperienceService;

    @PostMapping(value = "")
    public DataResult<JobExperience> saveJobExperience(@Valid @RequestBody JobExperience jobExperience){
        return jobExperienceService.saveJobExperience(jobExperience);
    }

    @GetMapping(value = "/{resumeId}")
    public DataResult<List<JobExperience>> findAllByBeginDate(@PathVariable int resumeId){
        return jobExperienceService.findByResume_Id(resumeId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }
}
