package kr.co.arterium.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// JWTFilter를 addFilterBefore로 JWTSecurityConfig에 등록
@Configuration
@RequiredArgsConstructor
public class JWTSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter jwtFilter = new JWTFilter(tokenProvider); // JWTFilter 객체를 생성하여 TokenProvider를 주입
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}


