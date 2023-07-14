package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.UserDTO;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String email){
        return userRepository.findByEmail(email)
            .orElseThrow( () -> new IllegalArgumentException(email));
    }
}