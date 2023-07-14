package kr.co.arterium.domain.exhibition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "booking_site")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingSiteEntity {//예매사이트 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 예매사이트 id

    private String name;//예매 사이트 이름

    @Column(name = "site_url")
    private String siteUrl;//예매 사이트 url

    @Column(name = "icon_url")
    private String iconUrl;//아이콘 이미지 저장 url
}

