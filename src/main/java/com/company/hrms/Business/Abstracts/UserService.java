package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Entitites.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByEmail(String email) throws NotFoundException;

    User confirmUser(User user);
}
