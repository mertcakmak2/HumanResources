package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.AuthService;
import com.company.hrms.Entities.Dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto) throws Exception {
        return authService.login(loginDto);
    }

}
