package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewDTO {

    private String content;
    private int rating;

}