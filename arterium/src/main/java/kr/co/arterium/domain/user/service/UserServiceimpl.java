package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService{
    private final UserRepository userRepository;

}
