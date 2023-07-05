package kr.co.arterium.domain.user.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(columnDefinition = "TEXT", name="profile_url")
    private String profileUrl;

    @Column(columnDefinition = "int default 1", name="user_level")
    private int userLevel;

    @Column(columnDefinition = "int default 30", name="role")
    private int roleId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // TODO - post 랑 연결

    // == 생성 메서드 == /
    @Builder(builderMethodName = "createUser")
    public UserEntity(Long id, String password, String name, String nickname, String email, String phone, LocalDateTime birthdate, String gender, String profileUrl, int userLevel, int roleId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
        this.profileUrl = profileUrl;
        this.userLevel = userLevel;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // == 메서드
    public void encryptPassword(String password){
        this.password = password;
    }


    // == 스프링 시큐리티 ==/
    @Override // 권한 반한
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override // 사용자의 id 반환
    public String getUsername() {
        return email;
    }

    @Override // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true;
    }
}
