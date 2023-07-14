package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    Optional<ReviewEntity> findById(@Param("id") Long id);
    List<ReviewEntity> findAllByPostId(@Param("post_id") Long postId);
}
