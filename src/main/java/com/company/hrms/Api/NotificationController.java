package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.NotificationService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Entities.Concretes.Notification;
import com.company.hrms.Entities.Dtos.Notification.NotificationSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<Notification> sendNotification(@RequestBody NotificationSaveDto notificationSaveDto) throws NotFoundException {
        return notificationService.sendNotification(notificationSaveDto);
    }

    @PostMapping(value = "/seenNotifications")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<Notification>> seenNotification(@RequestBody List<Notification> notifications) {
        return notificationService.seenNotification(notifications);
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<Notification>> findNotificationsByUserId(@PathVariable int userId) {
        return notificationService.findNotificationsByUserId(userId);
    }
}
