package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobAnnounceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.JobAnnounce;
import com.company.hrms.Entities.Dtos.Job.JobActiveAnnouncesDto;
import com.company.hrms.Entities.Dtos.Job.JobAnnounceFilterDto;
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
public class JobAnnounceController {

    private final JobAnnounceService jobAnnounceService;

    @GetMapping(value = "/findAllActiveJobs")
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobs() {
        return jobAnnounceService.findAllActiveJobs();
    }

    @GetMapping(value = "/findAllActiveJobsBySortingDate")
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobsBySortingDate() {
        return jobAnnounceService.findAllActiveJobsBySortingDate();
    }

    @GetMapping(value = "/findAllActiveJobByCompanyName")
    public DataResult<List<JobActiveAnnouncesDto>> findAllActiveJobByCompanyName(@RequestParam String companyName) {
        return jobAnnounceService.findAllActiveJobByCompanyName(companyName);
    }

    @PostMapping (value = "")
    public DataResult<JobAnnounce> announceJob(@Valid @RequestBody JobAnnounce jobAnnounce) throws Exception {
        return jobAnnounceService.announceJob(jobAnnounce);
    }

    @GetMapping(value = "/closeJobAnnounce")
    public Result closeJobAnnounce(@RequestParam int id) {
        return jobAnnounceService.closeJobAnnounce(id);
    }

    @GetMapping(value = "/activateJobAnnounceBySystemUser/{jobId}/{systemUserId}")
    public DataResult<JobAnnounce> activateJobAnnounceBySystemUser(@PathVariable int jobId, @PathVariable int systemUserId){
        return jobAnnounceService.activateJobAnnounceBySystemUser(jobId, systemUserId);
    }

    @PostMapping(value = "/findByCityIdAndJobTypeId")
    public DataResult<List<JobActiveAnnouncesDto>> findByCityIdAndJobTypeId(
            @RequestBody JobAnnounceFilterDto jobAnnounceFilterDto){
        return jobAnnounceService.findByCityIdAndJobTypeId(jobAnnounceFilterDto);
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
