package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Dto.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto) throws Exception;

}
