package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.dto.LoginDTO;
import kr.co.arterium.domain.user.dto.TokenDTO;
import kr.co.arterium.domain.user.entity.AuthorityEntity;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.repository.AuthorityRepository;
import kr.co.arterium.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    public void init() {
        AuthorityEntity defaultAuthority = new AuthorityEntity("ROLE_USER");
        authorityRepository.save(defaultAuthority);
    }
    @Override
    @Transactional
    public Long signUp(AddUserRequestDTO dto) {
        AuthorityEntity authority = authorityRepository.findByAuthorityName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default authority not found"));

        UserEntity userEntity = UserEntity.createUser()
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birthdate(dto.getBirthdate())
                .gender(dto.getGender())
                .profileUrl(dto.getProfileUrl())
                .userLevel(dto.getUserLevel())
                .roleId(dto.getRoleId())
                .authorities(Collections.singleton(authority))
                .build();

        return userRepository.save(userEntity).getId();
    }

    @Override
    public Optional<UserEntity> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public TokenDTO authenticate(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Invalid email or password");
        }

        UserEntity userEntity = optionalUser.get();
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new UsernameNotFoundException("Invalid email or password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
        return tokenService.generateToken(authentication);
    }


/*    *//**
     * 회원가입 signUp
     * @param dto
     * @return
     *//*
    @Override
    @Transactional
    public Long signUp(AddUserRequestDTO dto) {
        UserEntity userEntity = dto.toEntity(dto);

        // 비밀번호 암호화
        userEntity.encryptPassword(passwordEncoder.encode(dto.getPassword()));

        System.out.println("userEntity = " + userEntity);

        return userRepository.save(userEntity).getId();
    }

    *//**
     * user의 id(pk)값으로
     * @param id
     * @return
     *//*
    @Override
    public Optional<UserEntity> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }*/
}
