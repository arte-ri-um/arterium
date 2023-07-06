package kr.co.arterium.domain.user.controller;

import kr.co.arterium.common.response.ApiResponse;
import kr.co.arterium.domain.user.dto.AddUserRequestDTO;
import kr.co.arterium.domain.user.dto.UserDTO;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // TODO : 예외처리 해야한다.
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody AddUserRequestDTO userRequestDTO){
        Long signedUserId = userService.signUp(userRequestDTO);

        ApiResponse apiResponse = ApiResponse.create(
            HttpStatus.CREATED,
            "User created successfully",
            Collections.singletonMap("id", signedUserId)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
