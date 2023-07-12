package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Long create(ReviewDTO reviewDto, Long userId, Long postId);
    Optional<ReviewEntity> findOneById(Long id);
    List<ReviewEntity> findReviews();
    List<ReviewEntity> findReviewsByPostId(Long postId);
}
