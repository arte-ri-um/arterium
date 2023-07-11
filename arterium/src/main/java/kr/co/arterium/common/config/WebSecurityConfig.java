package kr.co.arterium.common.config;

import kr.co.arterium.common.jwt.JWTSecurityConfig;
import kr.co.arterium.common.jwt.JwtAccessDeniedHandler;
import kr.co.arterium.common.jwt.JwtAuthenticationEntryPoint;
import kr.co.arterium.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import kr.co.arterium.domain.user.service.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    //private final JwtManager jwtManager;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 스프링 시큐리티 기능 비활성화
    // 스프링 부트에서 제공하는 모든 정적 리소스 무시
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .csrf( csrf -> csrf.disable()) // TODO : csrf 비활성화 배포 시 수정
                .authorizeHttpRequests(authorize -> // 인증, 인가 설정
                        authorize
                                .antMatchers("/**").permitAll()
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login
                                //.loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                )
                .logout( logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                ) //;
                // 401, 403 Exception
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // JWTSecurityConfig 적용
                .and()
                .apply(new JWTSecurityConfig(tokenProvider, userDetailsService));
             //   .and()
               // .build();
              /*  .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtManager), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtManager))
                .userDetailsService(userDetailService)*/;

        return http.build();
    }


    // 패스워드 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return securityFilterChain(null).getAuthenticationManager();
    }*/
}