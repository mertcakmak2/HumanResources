package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.AuthService;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Dtos.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto) throws Exception {
        return authService.login(loginDto);
    }

    @ExceptionHandler(value = {
            UsernameNotFoundException.class,
            IllegalStateException.class,
            Exception.class
    })
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
    }
}
