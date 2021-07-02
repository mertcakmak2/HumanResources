package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ResumeService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Concretes.EmployerUpdateRequest;
import com.company.hrms.Entities.Dtos.Resume.ResumeSaveDto;
import com.company.hrms.Entities.Dtos.Resume.ResumeUpdateDto;
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
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping(value = "/{jobSeekerId}")
    public DataResult<Resume> findByJobSeekerId(@PathVariable int jobSeekerId){
        return resumeService.findByJobSeekerId(jobSeekerId);
    }

    @GetMapping(value = "")
    public DataResult<List<Resume>> findAllResumes() {
        return resumeService.findAllResumes();
    }

    @PostMapping(value = "")
    public Result saveResume(@Valid @RequestBody ResumeSaveDto resumeSaveDto) {
        return resumeService.saveResume(resumeSaveDto);
    }

    @PostMapping(value = "/update")
    public DataResult<Resume> updateResume(@RequestBody ResumeUpdateDto resumeUpdateDto) {
        return resumeService.updateResume(resumeUpdateDto);
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
