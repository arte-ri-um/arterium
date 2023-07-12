package kr.co.arterium.domain.exhibition.entity;

import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.*;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column
    private int rating;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

//    public void setMember(UserEntity user){
//        // 기존 관계 제거
//        if(this.user != null){
//            this.user.getReviews().remove(this);
//        }
//        this.user = user;
//        user.getReviews().add(this);
//    }

    @Builder
    public ReviewEntity(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}