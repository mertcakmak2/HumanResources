package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.AuthService;
import com.company.hrms.Core.DataAccess.UserDao;
import com.company.hrms.Core.Entitites.User;
import com.company.hrms.Core.Jwt.JwtUtil;
import com.company.hrms.Entities.Dtos.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginDto loginDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = userDao.findByEmail(loginDto.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("Wrong password or email"));

        if(!user.getIsConfirmed() || !user.getIsActive())
            throw new AuthenticationException("Kullanıcı onaylanmamış yada silinmiş.");

        boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) throw new IllegalStateException("Geçersiz kullanıcı adı yada şifre");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), user.getPassword()));
        } catch(Exception e) {
            throw new Exception("authentication failed "+e);
        }

        return jwtUtil.generateToken(loginDto.getEmail());
    }
}
