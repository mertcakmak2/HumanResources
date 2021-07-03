package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.LanguageService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Language;
import com.company.hrms.Entities.Dtos.Language.LanguageSaveDto;
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
@RequestMapping("/api/language")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping(value = "/{resumeId}")
    public DataResult<List<Language>> findAllLanguageByResumeId(@PathVariable int resumeId) {
        return languageService.findAllLanguageByResumeId(resumeId);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public DataResult<Language> saveLanguage(@Valid @RequestBody LanguageSaveDto languageSaveDto) {
        return languageService.saveLanguage(languageSaveDto);
    }

    @PostMapping(value = "/update")
    public DataResult<Language> updateLanguage(@Valid @RequestBody Language language) {
        return languageService.updateLanguage(language);
    }

    @DeleteMapping(value = "/{languageId}")
    public DataResult<Language> deleteLanguage(@PathVariable int languageId) {
        return languageService.deleteLanguage(languageId);
    }

    //Controller Advice yazıldı exceptionlar orada yakalanıyor

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        ValidationExceptionHandler validationExceptionHandler = new ValidationExceptionHandler();
        return validationExceptionHandler.getValidateExceptions(exceptions);
    }
}
