package com.fif.employeemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/employeeAdd.zul").hasRole("ADMIN")
                .antMatchers("/employeeDetail.zul").hasAnyRole("ADMIN", "USER")
                .antMatchers("/employeeList.zul").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login.zul")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/employeeList.zul")
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/accessDenied.zul");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
