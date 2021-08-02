package com.company.hrms.Config;

import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.Core.Jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;
    private final UserService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        //http.csrf().disable().authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/register/job-seeker").permitAll()
                .antMatchers("/api/register/employer").permitAll()
                .antMatchers("/api/register/job-seeker/confirm").permitAll()
                .antMatchers("/api/register/employer/confirm").permitAll()
                .antMatchers("/api").permitAll()
                /*.antMatchers("/api/register/job-seeker").permitAll()
                .antMatchers("/api/register/job-seeker/confirm").permitAll()
                .antMatchers("/api/register/employer").permitAll()
                .antMatchers("/api/register/employer/confirm").permitAll()*/
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
