package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.JobSeeker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/job-seekers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<JobSeeker>> findAllJobSeekers(){
        return jobSeekerService.findAllJobSeekers();
    }

    @GetMapping(value = "/{email}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<JobSeeker> findJobSeekerByEmail(@PathVariable String email) throws NotFoundException {
        return jobSeekerService.findJobSeekerByEmail(email);
    }

    @PostMapping(value = "/profile-picture")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public Result setResumeProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam int resumeId) throws NotFoundException {
        return jobSeekerService.saveJobSeekerProfilePic(file, resumeId);
    }

    @ExceptionHandler(value = {
            ValidationException.class,
            EntityNotFoundException.class,
            EntityExistsException.class,
            NotFoundException.class
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
