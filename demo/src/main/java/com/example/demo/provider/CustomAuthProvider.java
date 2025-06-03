package com.example.demo.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.example.demo.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.user.User;

@Component
@RequiredArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider{

    @Autowired
    private final UserService userService;
    // private final PasswordEncoder passEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException{
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.loadUserByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            return new UsernamePasswordAuthenticationToken(
                username,
                password,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }else{
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}