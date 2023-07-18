package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long id;    // 리뷰 아이디
    private Long postId;    // 포스트 아이디
    private Long userId;    // 유저 아이디
    private String userNickname;    // 유저 닉네임
    private String userProfileUrl;  // 유저 프로필 이미지 url
    private String content;     // 리뷰 내용
    private LocalDateTime createdAt;    // 생성일시
    private LocalDateTime updatedAt;    // 수정일시
    private int rating;     // 별점

}