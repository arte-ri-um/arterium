package kr.co.arterium.domain.exhibition.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDTO {

    private Long postId;    // 포스트 아이디
    private Long userId;    // 유저 아이디
    private String content;     // 리뷰 내용
    private int rating;     // 별점

}