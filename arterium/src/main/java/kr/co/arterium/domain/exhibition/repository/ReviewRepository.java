package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    Optional<ReviewEntity> findById(@Param("id") Long id);
    List<ReviewEntity> findByPostIdOrderByIdDesc(@Param("post_id") Long postId);
    // 포스트 id로 대상 리뷰 list를 찾은 후 리뷰 id를 기준으로 내림차순 정렬하여 반환

}
