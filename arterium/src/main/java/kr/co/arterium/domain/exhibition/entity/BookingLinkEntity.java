package kr.co.arterium.domain.exhibition.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "booking_links")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingLinkEntity {//예매 사이트 url 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //예매사이트 url id

    @Column(name = "post_id")
    private Long postId; //포스트id

    @Column(name = "site_id")
    private Long siteId; //예매사이트 id

    @Column(name = "booking_url")
    private String bookingUrl; //예매 링크
}
