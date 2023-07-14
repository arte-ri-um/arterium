package kr.co.arterium.domain.user.controller;

import kr.co.arterium.common.response.ApiResponse;
import kr.co.arterium.domain.user.dto.LoginDTO;
import kr.co.arterium.domain.user.dto.TokenDTO;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private final TokenService tokenService;

    @PostMapping("/authenticate")
    public ApiResponse authenticate(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        Optional<UserEntity> optionalUser = tokenService.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            return ApiResponse.create(HttpStatus.UNAUTHORIZED, "Invalid email or password", null, null);
        }

        UserEntity userEntity = optionalUser.get();
        if (!userEntity.getPassword().equals(password)) {
            return ApiResponse.create(HttpStatus.UNAUTHORIZED, "Invalid email or password", null, null);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
       // String token = tokenService.generateToken(authentication);
        TokenDTO tokenDTO = tokenService.generateToken(authentication);
        String token = tokenDTO.getToken();
        return ApiResponse.create(HttpStatus.OK, "Authentication successful", null, token);
    }
}
