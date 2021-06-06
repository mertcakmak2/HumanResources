package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping(value = "")
    public DataResult<List<Employer>> findAllEmployers(){
        return employerService.findAllEmployers();
    }
}
