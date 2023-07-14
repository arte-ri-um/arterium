package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import kr.co.arterium.domain.exhibition.mapper.ReviewMapper;
import kr.co.arterium.domain.exhibition.repository.ReviewRepository;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final PostService postService;


    // 리뷰 생성
    @Override
    @Transactional
    public Long create(ReviewDTO reviewDto, Long userId, Long postId) {
        ReviewEntity review = ReviewMapper.MAPPER.toEntity(reviewDto);
        UserEntity user = userService.findByUserId(userId).get();
        PostEntity post = postService.findByPostId(postId).get();
        review.setUser(user);
        review.setPost(post);

        return reviewRepository.save(review).getId();
    }
    
    // 단일 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public Optional<ReviewEntity> findOneById(Long id) {
        return reviewRepository.findById(id);
    }
    
    // 전체 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public List<ReviewEntity> findReviews() {
        return reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    // post에 대한 전체 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public List<ReviewEntity> findReviewsByPostId(Long postId) {
        return reviewRepository.findAllByPostId(postId);
    }

}
