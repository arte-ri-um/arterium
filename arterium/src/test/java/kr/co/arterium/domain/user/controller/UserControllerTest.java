package kr.co.arterium.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.repository.UserRepository;
import kr.co.arterium.domain.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.deleteAll();
    }

//    @AfterEach
//    public void afterEach() {
//        userRepository.deleteAll();
//    }

    @Test
    public void 회원가입_컨트롤러_테스트() throws Exception {
        //given
        String url = "/api/user";
        String email = "s@email.com";
        String password = "1234";
        String nickname = "영롱한 고양이";
        String phone = "01011112222";

        AddUserRequestDTO requestDTO = AddUserRequestDTO.builder()
            .email(email)
            .password(password)
            .nickname(nickname)
            .phone(phone)
            .build();

        // 객체 JSON 으로 직렬화
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody));

        //then
        System.out.println("resultActions = " + resultActions.andReturn().getResponse().getContentAsString());

    }

}