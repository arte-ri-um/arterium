package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.ImageTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageTempRepository extends JpaRepository<ImageTempEntity, Long> { //임시 이미지 repository
}
