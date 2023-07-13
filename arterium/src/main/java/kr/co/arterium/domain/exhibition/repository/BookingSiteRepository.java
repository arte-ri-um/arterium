package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSiteRepository extends JpaRepository<BookingSiteEntity, Long> { // 예매사이트 repository
}
