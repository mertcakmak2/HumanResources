package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.SkillService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Skill;
import com.company.hrms.Entities.Dtos.Skill.SkillSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
@RequestMapping("/api/skill")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SkillController {

    private final SkillService skillService;

    @GetMapping(value = "/{resumeId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<Skill>> findAllSkillByResumeId(@PathVariable int resumeId){
        return skillService.findAllSkillByResumeId(resumeId);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Skill> saveSkill(@Valid @RequestBody SkillSaveDto skillSaveDto){
        return skillService.saveSkill(skillSaveDto);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Skill> updateSkill(@Valid @RequestBody Skill skill){
        return skillService.updateSkill(skill);
    }

    @DeleteMapping(value = "/{skillId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Skill> deleteSkill(@PathVariable int skillId){
        return skillService.deleteSkill(skillId);
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
