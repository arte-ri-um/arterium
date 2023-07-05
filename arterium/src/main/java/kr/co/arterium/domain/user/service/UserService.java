package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Long signUp(AddUserRequestDTO dto);
    Optional<UserEntity> findByUserId(Long id);
    Optional<UserEntity> findUserByEmail(String email);
}
