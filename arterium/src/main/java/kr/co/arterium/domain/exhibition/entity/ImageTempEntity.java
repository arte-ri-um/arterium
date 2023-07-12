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
public class ImageTempEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name="original_name")
    private String originalName;

    @Column(nullable = false,name="save_name")
    private String saveName;

    @Column(nullable = false,name="file_size")
    private Long fileSize;

    @Column(nullable = false,name="file_ext")
    private String fileExt;

    @Column(nullable = false,name="register_date")
    private LocalDateTime registerDate;
}
