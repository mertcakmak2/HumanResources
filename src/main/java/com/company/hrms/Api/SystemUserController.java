package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.SystemUserService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.SystemUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/system-user")
@RequiredArgsConstructor
public class SystemUserController {

    private final SystemUserService systemUserService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<SystemUser>> findAllSystemUsers(){
        return systemUserService.findAllSystemUsers();
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<SystemUser> updateSystemUser(@RequestBody SystemUser systemUser){
        return systemUserService.updateSystemUser(systemUser);
    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
    })
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
    }
}
