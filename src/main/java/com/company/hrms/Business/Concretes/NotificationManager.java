package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.NotificationService;
import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.Core.Entitites.User;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.NotificationDao;
import com.company.hrms.Entities.Concretes.Notification;
import com.company.hrms.Entities.Dtos.Notification.NotificationSaveDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationManager implements NotificationService {

    private final UserService userService;
    private final NotificationDao notificationDao;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public DataResult<Notification> sendNotification(NotificationSaveDto notificationSaveDto) throws NotFoundException {

        User fromUser = userService.findUserByEmail(notificationSaveDto.getFromUserEmail());
        User toUser = userService.findUserByEmail(notificationSaveDto.getToUserEmail());

        Notification notification = Notification.builder()
                .from(fromUser)
                .createdAt(new Date())
                .to(toUser).build();
        rabbitTemplate.convertAndSend("profile-view-exchange","profile-view-routing-key",notification);
        return new SuccessDataResult<Notification>(notificationDao.save(notification),"Bildirim gönderildi.");
    }

    @Override
    public DataResult<List<Notification>> seenNotification(List<Notification> notifications) {
        // Todo: Gönderilen bildirimlerin seen alanını true yap.
        return null;
    }

    @Override
    public DataResult<List<Notification>> findNotificationsByUserId(int userId) {
        return new SuccessDataResult<List<Notification>>(
                notificationDao.findByToIdAndSeen(userId),
                "Görüntülenmemiş bildirimler getirildi."
        );
    }

    @RabbitListener(queues = "profile-view-notification")
    public void consumeNotification(Notification notification) {
        System.out.println("listener");
        System.out.println(notification.getTo().getEmail());
        // TODO web socket'e yolla.
    }
}
