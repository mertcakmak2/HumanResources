package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.WorkingConceptService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.WorkingConcept;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/working-concept")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class WorkingConceptController {

    private final WorkingConceptService workingConceptService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<WorkingConcept>> findAllWorkingConcepts(){
        return workingConceptService.findAllWorkingConcepts();
    }
}
