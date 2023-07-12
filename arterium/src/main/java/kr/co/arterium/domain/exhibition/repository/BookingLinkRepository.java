package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingLinkRepository extends JpaRepository<BookingLinkEntity, Long> {    // 예매사이트Link repository
    List<BookingLinkEntity> findAllByPostId(Long postId);
}
