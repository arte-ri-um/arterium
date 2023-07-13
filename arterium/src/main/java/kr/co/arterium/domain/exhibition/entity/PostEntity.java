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
public class PostEntity {//포스트 생성 정보 저장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //포스트ID
    
    private String title;   //포스트 제목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user; //사용자 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private ExhibitionEntity exhibition;  //전시장 ID

    @Column(name = "start_date")
    private LocalDate startDate;    //시작날짜

    @Column(name = "end_date")
    private LocalDate endDate;  //끝난날짜

    @Column(name = "viewing_time")
    private String viewingTime; //관람시간

    @Column(name = "age_restriction")
    private String ageRestriction;  //연령제한

    @Column(name = "price")
    private int price;  //가격

    @Column(name = "summary", length = 500)
    private String summary; //요약

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; //내용

    @Column(name = "origin_url", length = 500)
    private String originUrl;    //전시 정보가 있는 원래 url

    @Column(name = "is_eligibility")
    private int isEligibility;  //일찍 예약 가능여부

    @Column(name = "eligibility_date")
    private LocalDate eligibilityDate;  // 일찍 예약 시작 날짜

    @Column(name = "reg_date")
    private LocalDateTime regDate;  //post생성 시간

    @Column(name = "post_url")
    private String postUrl; //예약 url

    @PostPersist
    private void generatePostUrl() { //포스트 생성 시 url 생성
        this.postUrl = "/artPost/post/" + this.id;
    }
}
