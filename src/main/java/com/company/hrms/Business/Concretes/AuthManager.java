package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.AuthService;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.User;
import com.company.hrms.Entities.Dto.LoginDto;
import com.company.hrms.Jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) throw new Exception("Geçersiz kullanıcı adı yada şifre");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), user.getPassword()));
        } catch(Exception e) {
            throw new Exception("authentication failed "+e);
        }

        return jwtUtil.generateToken(loginDto.getEmail());
    }

}
