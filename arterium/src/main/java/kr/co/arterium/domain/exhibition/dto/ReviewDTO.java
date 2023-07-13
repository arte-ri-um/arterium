package kr.co.arterium.domain.exhibition.dto;

import lombok.*;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewDTO {

    private String content;
    private int rating;

}