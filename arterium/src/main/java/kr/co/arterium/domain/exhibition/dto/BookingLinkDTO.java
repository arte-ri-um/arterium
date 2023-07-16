package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.exhibition.entity.PostEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingLinkDTO {   //예매 사이트 url 저장
    private Long id;    //예매사이트 url id
    private Long postId;    //포스트id
    private Long siteId;    //예매사이트 id
    private String bookingUrl;  //예매 링크

}
