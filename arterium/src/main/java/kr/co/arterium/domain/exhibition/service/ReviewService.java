package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.ReviewRequestDTO;
import kr.co.arterium.domain.exhibition.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    Long save(ReviewRequestDTO reviewDto);      // 리뷰 저장
    ReviewResponseDTO findOneById(Long id);     // 단일 리뷰 찾기
    List<ReviewResponseDTO> findReviews();      // 전체 리뷰 찾기
    List<ReviewResponseDTO> findReviewsByPostId(Long postId);   // 특정 post에 대한 전체 리뷰 찾기
    void update(ReviewRequestDTO reviewDto, Long id);       // 리뷰 수정
    void delete(Long id);       // 리뷰 삭제
}
