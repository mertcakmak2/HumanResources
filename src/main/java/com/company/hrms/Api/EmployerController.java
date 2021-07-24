package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import com.company.hrms.Entities.Dtos.Employer.EmployerCompanyUpdateDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<Employer>> findAllEmployers(){
        return employerService.findAllEmployers();
    }

    @GetMapping(value = "/{email}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Employer> findEmployerByEmail(@PathVariable String email) throws NotFoundException {
        return employerService.findEmployerByEmail(email);
    }

    @PostMapping(value = "/update/approve")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<EmployerUpdateRequest> approveForUpdateEmployerCompany(
            @Valid @RequestBody EmployerCompanyUpdateDto employerCompanyUpdateDto, @RequestParam int employerId ) throws Exception {
        return employerService.approveForUpdateEmployerCompany(employerCompanyUpdateDto, employerId);
    }

    @GetMapping(value = "/update/confirm")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Employer> updateEmployerCompany( @RequestParam int systemUserId, @RequestParam int employerId) throws NotFoundException {
        return employerService.updateEmployerCompany(systemUserId, employerId);
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
