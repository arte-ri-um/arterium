package kr.co.arterium.domain.exhibition.entity;

import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private ExhibitionEntity exhibition;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "viewing_time")
    private String viewingTime;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "price")
    private int price;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "origin_url", length = 500)
    private String originUrl;

    @Column(name = "is_eligibility")
    private int isEligibility;

    @Column(name = "eligibility_date")
    private LocalDate eligibilityDate;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "post_url")
    private String postUrl;

    @PostPersist
    private void generatePostUrl() {    //사실상 불필요 , 하지만 그냥 해봄
        this.postUrl = "/artPost/post/" + this.id;
    }
}
