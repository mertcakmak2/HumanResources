package com.company.hrms.Entities.Dtos.Notification;

import com.company.hrms.Core.Entitites.User;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class NotificationSaveDto {

    private String fromUserEmail;

    private String toUserEmail;

}
