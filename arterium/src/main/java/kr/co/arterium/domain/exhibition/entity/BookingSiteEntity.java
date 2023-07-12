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
public class BookingSiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "site_url")
    private String siteUrl;

    @Column(name = "icon_url")
    private String iconUrl;
}

