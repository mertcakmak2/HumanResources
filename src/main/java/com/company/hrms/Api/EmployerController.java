package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.EmployerService;
import com.company.hrms.Entities.Concretes.Employer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public List<Employer> findAllEmployers(){
        return employerService.findAllEmployers();
    }
}
