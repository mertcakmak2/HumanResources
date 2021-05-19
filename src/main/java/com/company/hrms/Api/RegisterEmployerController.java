package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.RegisterEmployerService;
import com.company.hrms.Entities.Concretes.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register/employer")
@RequiredArgsConstructor
public class RegisterEmployerController {

    private final RegisterEmployerService registerEmployerService;

    @PostMapping("")
    public Employer registerEmployer(@RequestBody Employer employer) throws Exception {
        return registerEmployerService.registerEmployer(employer);
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
