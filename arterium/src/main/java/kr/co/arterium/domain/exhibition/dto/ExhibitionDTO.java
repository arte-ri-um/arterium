package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ExhibitionDTO { //전시장
    private Long id;    // 전시장 아이디
    private String name;    //전시장 이름
    private String location;    //전시장 위치 -> 나중에 api로 저장
    private String holidayInfo; // 휴일 정보
    private String discountInfo;    //할인정보
    private String notice;  // 유의사항
    private String siteUrl; // 사이트 정보
}
