package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException(email));
    }
}