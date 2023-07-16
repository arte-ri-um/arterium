package kr.co.arterium.common.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//  JWT 토큰을 사용하여 사용자의 인증과 인가를 처리하는 역할을 수행
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;


    // 토큰 인증정보 SecurityContext에 저장
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);
        String requestURI = request.getRequestURI();

        // resolvetoken을 통해 토큰 받아와 유효성 검증
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            // 토큰에서 유저네임, 권한을 뽑아 스프링 시큐리티 유저를 만들어 Authentication 반환
            Authentication authentication = tokenProvider.getAuthentication(token);
            // 해당 스프링 시큐리티 유저를 시큐리티 컨텍스트에 저장, 즉 디비를 거치지 않음
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            LOGGER.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header에서 토큰 정보를 꺼내옴
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

