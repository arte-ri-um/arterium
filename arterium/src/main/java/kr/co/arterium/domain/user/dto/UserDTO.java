package kr.co.arterium.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 사용자 정보 담기
// 유저 조회
@Getter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private LocalDateTime birthdate;
    private String gender;
    private String profileUrl;
    private int userLevel;
    private int roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserDTO(Long id, String password,String nickname, String email, String phone, LocalDateTime birthdate, String gender, String profileUrl, int userLevel, int role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
        this.profileUrl = profileUrl;
        this.userLevel = userLevel;
        this.roleId = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}