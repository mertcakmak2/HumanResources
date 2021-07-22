package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.SchoolService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.School;
import com.company.hrms.Entities.Dtos.School.SchoolSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping(value = "/{resumeId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<School>> findAllSchoolByResumeId(@PathVariable int resumeId){
        return schoolService.findAllSchoolByResumeId(resumeId);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<School> saveSchool(@Valid @RequestBody SchoolSaveDto schoolSaveDto){
        return schoolService.saveSchool(schoolSaveDto);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<School> updateSchool(@Valid @RequestBody School school){
        return schoolService.updateSchool(school);
    }

    @DeleteMapping(value = "/{schoolId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<School> deleteSchool(@PathVariable int schoolId){
        return schoolService.deleteSchool(schoolId);
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
