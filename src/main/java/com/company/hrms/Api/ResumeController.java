package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.ResumeService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Entities.Concretes.ProfilePicture;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Dtos.ResumeSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping(value = "")
    public DataResult<List<Resume>> findAllResumes() {
        return resumeService.findAllResumes();
    }

    @PostMapping(value = "")
    public Result saveResume(@Valid @RequestBody ResumeSaveDto resumeSaveDto) {
        return resumeService.saveResume(resumeSaveDto);
    }

    @PostMapping(value = "/profile-picture")
    public Result setResumeProfilPicture(@RequestParam("file") MultipartFile file, @RequestParam int resumeId) {
        return resumeService.setResumeProfilPicture(file, resumeId);
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
