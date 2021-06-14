package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.WorkingConceptService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.WorkingConcept;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/working-concept")
@RequiredArgsConstructor
public class WorkingConceptController {

    private final WorkingConceptService workingConceptService;

    @GetMapping(value = "")
    public DataResult<List<WorkingConcept>> findAllWorkingConcepts(){
        return workingConceptService.findAllWorkingConcepts();
    }
}
