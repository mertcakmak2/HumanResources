package com.company.hrms.Entities.Dtos.Notification;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NotificationSaveDto {

    @NotBlank
    private String fromUserEmail;

    @NotBlank
    private String toUserEmail;

}
