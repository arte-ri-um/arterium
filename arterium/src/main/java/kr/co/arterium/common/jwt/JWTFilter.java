package kr.co.arterium.common.jwt;

import kr.co.arterium.domain.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



//  JWT 토큰을 사용하여 사용자의 인증과 인가를 처리하는 역할을 수행
/*public class JWTFilter extends OncePerRequestFilter { // OncePerRequestFilter -> 중복 실행을 방지하여 한 요청에 대해 한 번만 실행되도록 보장하는 역할
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    public JWTFilter(TokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = extractJwtFromRequest(request);
        String requestURI = request.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            String email = tokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOGGER.info("Security Context에 '{}' 인증 정보를 저장했습니다. URI: {}", authentication.getName(), requestURI);
            }
        } else {
            LOGGER.info("유효한 JWT 토큰이 없습니다. URI: {}", requestURI);
        }

        filterChain.doFilter(request, response);
    }

*//*    // 로깅이나 예외처리 필요없을 경우
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // HTTP 요청에서 JWT 토큰 추출
        String jwt = extractJwtFromRequest(request);

        // JWT 토큰 유효성 검사
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            //JWT 이메일 추출
            String email = tokenProvider.getUsernameFromToken(jwt);

            // UserDetailsService에서 사용자 정보 조회
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // 조회된 사용자 정보가 있을 경우 인증 처리
            if (userDetails != null) {
                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // 인증에 대한 세부 정보 설정
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // SecurityContext(인증 정보 관리 객체)에 인증 객체 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 다음 필터
        filterChain.doFilter(request, response);
    }*//*

    private String extractJwtFromRequest(HttpServletRequest request) { // extractJwtFromRequest -> JWT 토큰을 HTTP 요청에서 추출하는 역할
        // Authorization 헤더에서 JWT 토큰 추출
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Bearer 접두사 제거 후 JWT 토큰 반환
            return bearerToken.substring(7);
        }
        return null;
    }
}*/

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
 /*   private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }*/
}

