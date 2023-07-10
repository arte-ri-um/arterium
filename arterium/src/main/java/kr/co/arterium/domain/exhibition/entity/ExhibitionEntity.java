package kr.co.arterium.domain.exhibition.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "exhibition")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String holidayInfo;

    @Column(nullable = false)
    private String discountInfo;

    @Column(nullable = false)
    private String notice;

    @Column(nullable = false)
    private String siteUrl;
}

