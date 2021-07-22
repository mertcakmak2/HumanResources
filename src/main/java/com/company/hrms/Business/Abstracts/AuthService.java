package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Entitites.User;
import com.company.hrms.Entities.Dtos.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto) throws Exception;
}
