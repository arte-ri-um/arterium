package kr.co.arterium.domain.exhibition.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "booking_links")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "post_id")
    private long postId;

    @Column(name = "site_id")
    private long siteId;

    @Column(name = "booking_url")
    private String bookingUrl;
}
