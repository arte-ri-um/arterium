package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingLinkRepository extends JpaRepository<BookingLinkEntity, Long> {
}
