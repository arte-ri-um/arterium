package kr.co.arterium.domain.exhibition.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "exhibition")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionEntity {//전시장 정보 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 전시장 아이디

    @Column(nullable = false)
    private String name; //전시장 이름

    @Column(nullable = false)
    private String location; //전시장 위치 -> 나중에 api로 저장

    @Column(nullable = false)
    private String holidayInfo;// 휴일 정보

    @Column(nullable = false)
    private String discountInfo; //할인정보

    @Column(nullable = false)
    private String notice;// 유의사항

    @Column(nullable = false)
    private String siteUrl;// 사이트 정보
}

