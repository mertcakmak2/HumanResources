package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.NotificationService;
import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.Core.Entitites.User;
import com.company.hrms.Core.Utilities.Result.*;
import com.company.hrms.DataAccess.Abstracts.NotificationDao;
import com.company.hrms.Entities.Concretes.Notification;
import com.company.hrms.Entities.Dtos.Notification.NotificationPageableDto;
import com.company.hrms.Entities.Dtos.Notification.NotificationSaveDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationManager implements NotificationService {

    private final UserService userService;
    private final NotificationDao notificationDao;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public Result sendNotification(NotificationSaveDto notificationSaveDto) {
        rabbitTemplate.convertAndSend(
                "profile-view-exchange",
                "profile-view-routing-key",
                notificationSaveDto );
        return new SuccessResult("Profil görüntüleme bildirimi gönderildi.");
    }

    @Override
    public Result seenNotification(List<Notification> notifications) {
        List<Integer> notificationIds = notifications.stream().map(n -> n.getId()).collect(Collectors.toList());
        notificationDao.seenNotifications(notificationIds);
        return new SuccessResult("Bildirimler güncellendi.");
    }

    @Override
    public DataResult<List<Notification>> findNotificationsByUserId(NotificationPageableDto notificationPageableDto, int userId) {

        Pageable pageable = PageRequest.of(
                notificationPageableDto.getPage(),
                notificationPageableDto.getSize(),
                Sort.by(Sort.Direction.DESC,"id"));

        int notificationCount = notificationDao.findCountByToId(userId);

        return new PaginationDataResult<List<Notification>>(
                notificationDao.findByToId(userId, pageable),
                "Bildirimler getirildi",
                notificationCount
        );
    }

    @RabbitListener(queues = "profile-view-notification")
    public void consumeNotification(NotificationSaveDto notificationSaveDto) throws NotFoundException {

        User fromUser = userService.findUserByEmail(notificationSaveDto.getFromUserEmail());
        User toUser = userService.findUserByEmail(notificationSaveDto.getToUserEmail());

        Notification notification = Notification.builder()
                .from(fromUser)
                .createdAt(new Date())
                .to(toUser)
                .build();

        notificationDao.save(notification);
        // TODO web socket'e yolla.
    }
}
