package com.company.hrms.Entities.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDto {

    private final String email;
    private final String password;
}
