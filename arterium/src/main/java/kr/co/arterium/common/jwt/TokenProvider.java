package kr.co.arterium.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.security.Key;

// Token의 생성, 인증정보 조회, 유효성 검증, 암호화 설정하는 클래스
@Component
public class TokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret; // JWT 서명을 위한 비밀키
    private final long tokenValidityInMilliseconds; // 토큰 유효기간을 밀리초로 설정
    private Key key; // 비밀키 기반 Key 객체

    private final UserRepository userRepository;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
                         UserRepository userRepository) {
        // this.secret = secret;
        // Base64로 인코딩한 secret 값 사용
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.userRepository = userRepository;
    }

   /* @PostConstruct // JWT라 없애도 됨...?
    public void init() {
        // secret 값을 Base64로 디코딩하여 키 생성
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }*/

    // 토큰 생성
    public String createToken(Authentication authentication) {
        // authentication에서 사용자 권한 정보 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // 쉼표로 권한 이름 연결

        // .yml에서 설정한 토큰 만료시간 설정
        //long now = System.currentTimeMillis();
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512) // HS512 알고리즘 사용
                .setExpiration(validity)
                .compact();
    }

    // 왜 있어야 하는 거지??
    // 삭제 완료
    //
    //
    //
  /*  public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }*/

    // JWT 토큰을 파라미터로 받아 claim 생성해 인증 정보 조회 후 인증 객체 반환
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject(); // 이메일 정보 가져오기

        // 이메일을 기반으로 유저 조회
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        UserEntity userEntity = optionalUser.get();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 디비를 거치지 않고 토큰에서 값을 꺼내 바로 시큐리티 유저 객체를 만들어 Authentication을 만들어 반환하기에 유저네임, 권한 외 정보는 알 수 없다.
     //   User principal = new User(claims.getSubject(), "", authorities);
        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            LOGGER.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            LOGGER.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            LOGGER.info("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            LOGGER.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
