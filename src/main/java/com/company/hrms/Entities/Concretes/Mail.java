package com.company.hrms.Entities.Concretes;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Mail implements Serializable {

    private String toMail;
    private String ccMail;
    private String url;
    private String token;
}
