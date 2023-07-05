package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원가입 signUp
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long signUp(AddUserRequestDTO dto) {
        UserEntity userEntity = dto.toEntity(dto);

        // 비밀번호 암호화
        userEntity.encryptPassword(passwordEncoder.encode(dto.getPassword()));

        System.out.println("userEntity = " + userEntity);

        return userRepository.save(userEntity).getId();
    }

    /**
     * user의 id(pk)값으로
     * @param id
     * @return
     */
    @Override
    public Optional<UserEntity> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
