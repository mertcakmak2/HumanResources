package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
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
    public DataResult<Job> announceJob(@Valid @RequestBody Job job) throws Exception {
        return jobService.announceJob(job);
    }

    @GetMapping(value = "/closeJobAnnounce")
    public Result closeJobAnnounce(@RequestParam int id) {
        return jobService.closeJobAnnounce(id);
    }

    @GetMapping(value = "/activateJobAnnounceBySystemUser/{jobId}/{systemUserId}")
    public DataResult<Job> activateJobAnnounceBySystemUser(@PathVariable int jobId, @PathVariable int systemUserId){
        return jobService.activateJobAnnounceBySystemUser(jobId, systemUserId);
    }

    @ExceptionHandler(value = {
            ValidationException.class,
            EntityNotFoundException.class
    })
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
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
