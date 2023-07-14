package kr.co.arterium.domain.exhibition.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temporary_images")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageTempEntity {  //임시 이미지 저장 -> 나중에 자세히 구현 예정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //이미지 id

    @Column(nullable = false,name="original_name")
    private String originalName;    // 이미지 원래 이름

    @Column(nullable = false,name="save_name")
    private String saveName;    // 이미지 저장 이름 

    @Column(nullable = false,name="file_size")
    private Long fileSize;  //파일 사이즈

    @Column(nullable = false,name="file_ext")
    private String fileExt; //파일 확장자

    @Column(nullable = false,name="register_date")
    private LocalDateTime registerDate; //등록일
}
