package kr.co.arterium.domain.exhibition.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PostViewDTO {  //포스트 view 정보
    private Long id;    //포스트ID
    private Long userId;    //사용자 ID
    private String userNickname;    //사용자 닉네임
    private ExhibitionDTO exhibitionDTO;    //전시장 정보
    private LocalDate startDate;    //시작날짜
    private LocalDate endDate;      //끝난날짜
    private String viewingTime; //관람시간
    private String ageRestriction;  //연령제한
    private Integer price;  //가격
    private String summary; //요약
    private String description; //내용
    private String originUrl;    //원url
    private Integer isEligibility;  //일찍 예약 가능여부
    private LocalDate eligibilityDate;  // 일찍 예약 시작 날짜
    private List<BookingLinkDTO> bookingLinks;   //예약 url
}
