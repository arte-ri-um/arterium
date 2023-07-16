package kr.co.arterium.domain.user.controller;

import kr.co.arterium.common.jwt.TokenProvider;
import kr.co.arterium.common.response.ApiResponse;
import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.dto.LoginDTO;
import kr.co.arterium.domain.user.dto.TokenDTO;
import kr.co.arterium.domain.user.dto.UserDTO;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // TODO : 예외처리 해야한다.

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody AddUserRequestDTO requestDTO) {
        Long userId = userService.signUp(requestDTO);
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        TokenDTO tokenDTO = userService.authenticate(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}