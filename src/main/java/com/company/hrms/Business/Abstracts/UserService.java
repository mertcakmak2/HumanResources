package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Entitites.User;
import javassist.NotFoundException;

public interface UserService {

    User findUserByEmail(String email) throws NotFoundException;

    User confirmUser(User user);
}
