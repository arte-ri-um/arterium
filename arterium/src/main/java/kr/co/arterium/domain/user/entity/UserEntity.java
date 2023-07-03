package kr.co.arterium.domain.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 11)
    private String phone;

    private LocalDateTime birthdate;

    @Column(length = 1, columnDefinition = "varchar(1) default 'N'")
    private String gender;

    private String profileUrl;

    @Column(columnDefinition = "int default 1")
    private int userLevel;

    @Column(columnDefinition = "int default 30")
    private int role;

    @Column(name="created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;
}
