package kr.co.arterium.common.jwt;

import kr.co.arterium.domain.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TokenProvider 주입 받아 JwtFilter를 Security에 등록하기 위한 클래스
//@EnableWebSecurity
//@RequiredArgsConstructor
/*public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider; // 토큰 생성 및 검증
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // 인증 실패
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler; // 인가 실패

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/public/**", "/resources/**", "/static/**", "/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfigurer(tokenProvider)); // JWTConfigurer를 적용하여 JWT 필터를 등록
    }*/
@RequiredArgsConstructor
@Configuration
public class JWTSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final UserDetailService userDetailsService;


        @Override
        public void configure(HttpSecurity http) /*throws Exception*/ {
            JWTFilter jwtFilter = new JWTFilter(tokenProvider, userDetailsService); // JWTFilter 객체를 생성하여 TokenProvider를 주입
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
//}
