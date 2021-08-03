package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.NotificationService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.*;
import com.company.hrms.Entities.Concretes.Notification;
import com.company.hrms.Entities.Dtos.Notification.NotificationPageableDto;
import com.company.hrms.Entities.Dtos.Notification.NotificationSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public Result sendNotification(@Valid @RequestBody NotificationSaveDto notificationSaveDto) throws NotFoundException {
        return notificationService.sendNotification(notificationSaveDto);
    }

    @PostMapping(value = "/seenNotifications")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @ResponseStatus(HttpStatus.OK)
    public Result seenNotification(@RequestBody List<Notification> notifications) {
        return notificationService.seenNotification(notifications);
    }

    @PostMapping(value = "/{userId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @ResponseStatus(HttpStatus.OK)
    public DataResult<List<Notification>> findNotificationsByUserId(
            @RequestBody NotificationPageableDto notificationPageableDto, @PathVariable int userId) {
        return notificationService.findNotificationsByUserId(notificationPageableDto, userId);
    }

    @ExceptionHandler(value = {
            NotFoundException.class
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
