package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자 회원가입 테스트")
    void signUp() {
        // given
        AddUserRequestDTO requestDTO = AddUserRequestDTO.builder()
            .email("subin@mail.com")
            .password("1234")
            .name("테스트")
            .nickname("영롱한 고양이")
            .phone("01012345678")
            .build();

        // when
        Long createdId = userService.signUp(requestDTO);

        // then
    }
}