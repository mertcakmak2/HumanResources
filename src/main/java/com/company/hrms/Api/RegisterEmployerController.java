package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.RegisterEmployerService;
import com.company.hrms.Entities.Concretes.Employer;
import com.company.hrms.Entities.Dto.Employer.EmployerRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register/employer")
@RequiredArgsConstructor
public class RegisterEmployerController {

    private final RegisterEmployerService registerEmployerService;

    @PostMapping("")
    public Employer registerEmployer(@RequestBody EmployerRegisterDto employerRegisterDto) throws Exception {
        return registerEmployerService.registerEmployer(employerRegisterDto);
    }

    @GetMapping("/confirm")
    public String confirmWithEmail(@RequestParam String token) {
        return registerEmployerService.confirmWithEmail(token);
    }

    @GetMapping("/{employerId}/{systemUserId}")
    public String confirmWithSystemUser(@PathVariable int employerId, @PathVariable int systemUserId) {
        return registerEmployerService.confirmWithSystemUser(employerId, systemUserId);
    }
}
