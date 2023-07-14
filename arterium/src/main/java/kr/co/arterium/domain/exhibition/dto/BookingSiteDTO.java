package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BookingSiteDTO {   //예매사이트
    private Long id;    // 예매사이트 id
    private String name;    //예매 사이트 이름
    private String siteUrl; //예매 사이트 url
    private String iconUrl; //아이콘 이미지 저장 url
}

