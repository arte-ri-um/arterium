package kr.co.arterium.domain.user.service;

import kr.co.arterium.common.jwt.TokenProvider;
import kr.co.arterium.domain.user.dto.TokenDTO;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public TokenDTO generateToken(Authentication authentication) {
        String token = tokenProvider.createToken(authentication);
        return TokenDTO.builder()
                .token(token)
                .build();
    }

    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}