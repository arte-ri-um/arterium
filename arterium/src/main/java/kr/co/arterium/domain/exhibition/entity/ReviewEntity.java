package kr.co.arterium.domain.exhibition.entity;

import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 리뷰 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;            // 리뷰 대상 포스트 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;            // 리뷰 작성 유저 엔티티

    @Column(columnDefinition = "TEXT")
    private String content;             // 리뷰 내용

    @Column(name="created_at", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;        // 리뷰 작성일시

    @Column(name="updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;        // 리뷰 수정일시

    @Column
    private int rating;     // 별점

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

//    public void setUser(UserEntity user){
//        // 기존 관계 제거
//        if(this.user != null){
//            this.user.getReviews().remove(this);
//        }
//        this.user = user;
//        user.getReviews().add(this);
//    }

}