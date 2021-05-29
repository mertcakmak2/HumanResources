package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.User;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserDao userDao;

    @Override
    public User findUserByEmail(String email) throws NotFoundException {
        return userDao.findByEmail(email).orElseThrow(() ->
                new NotFoundException("User bulunamadı."));
    }

    @Override
    public User confirmUser(User user) {
        user.setIsConfirmed(true);
        return userDao.save(user);
    }
}
