package kr.co.arterium.domain.exhibition.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PostDTO {  //포스트 생성 정보
    private Long id;    //포스트ID
    private String title;   //포스트제목
    private Long userId;    //사용자 ID
    private Long exhibitionId;  //전시장 ID
    private LocalDate startDate;    //시작날짜
    private LocalDate endDate;      //끝난날짜
    private String viewingTime; //관람시간
    private String ageRestriction;  //연령제한
    private Integer price;  //가격
    private String summary; //요약
    private String description; //내용
    private String originUrl;    //전시 정보가 있는 원래 url
    private Integer isEligibility;  //일찍 예약 가능여부
    private LocalDate eligibilityDate;  // 일찍 예약 시작 날짜
    private List<BookingLinkDTO> bookingLinks;   //예약 url
}
