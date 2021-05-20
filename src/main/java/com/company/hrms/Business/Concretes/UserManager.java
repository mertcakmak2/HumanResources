package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElse(null);
        return userDao.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
