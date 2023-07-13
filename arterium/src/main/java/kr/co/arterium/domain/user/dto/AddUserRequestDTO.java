package kr.co.arterium.domain.user.dto;

import kr.co.arterium.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//사용자 추가 요청 정보
// 유저 생성 및 업데이트
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequestDTO {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private LocalDateTime birthdate;
    private String gender;
    private String profileUrl;
    private int userLevel;
    private int roleId;

    // toEntity 사용해 AddUserRequestDTO를 UserEntity로 변환
    public UserEntity toEntity(AddUserRequestDTO dto) {
        return UserEntity.createUser()
            .email(dto.getEmail())
            .password(dto.getPassword())
            .nickname(dto.getNickname())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .birthdate(dto.getBirthdate())
            .gender(dto.getGender())
            .profileUrl(dto.getProfileUrl())
            .userLevel(dto.getUserLevel())
            .roleId(dto.getRoleId())
            .build();
    }
}
