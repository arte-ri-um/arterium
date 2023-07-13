package kr.co.arterium.domain.user.dto;

import lombok.*;

// 토큰값 담기
@Getter
@Setter // setter 수정방법 찾기
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String token;
}