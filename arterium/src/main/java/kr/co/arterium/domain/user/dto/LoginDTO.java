package kr.co.arterium.domain.user.dto;

import lombok.*;

// @Valid 관련 어노테이션 추가 (not null/size 등)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}