package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.Notification;
import com.company.hrms.Entities.Dtos.Notification.NotificationSaveDto;
import javassist.NotFoundException;

import java.util.List;

public interface NotificationService {

    Result sendNotification(NotificationSaveDto notificationSaveDto) throws NotFoundException;

    DataResult<List<Notification>> seenNotification(List<Notification> notifications);

    DataResult<List<Notification>> findNotificationsByUserId(int userId);

}
