package kr.co.arterium.domain.user.service;

import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
            .nickname("영롱한 고양이")
            .phone("01012345678")
            .build();

        // when
        Long createdId = userService.signUp(requestDTO);
        Optional<UserEntity> user = userService.findByUserId(createdId);

        System.out.println("user = " + user.toString());


        // then
        Assertions.assertThat(user).isNotEmpty();
        Assertions.assertThat(user.get().getEmail()).isEqualTo(requestDTO.getEmail());


    }
}