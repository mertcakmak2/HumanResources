package com.company.hrms.Business.Abstracts;

import com.company.hrms.Entities.Concretes.User;
import javassist.NotFoundException;

public interface UserService {

    User findUserByEmail(String email) throws NotFoundException;

    User confirmUser(User user);
}
