package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.dto.ReviewRequestDTO;
import kr.co.arterium.domain.exhibition.dto.ReviewResponseDTO;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import kr.co.arterium.domain.exhibition.mapper.PostMapper;
import kr.co.arterium.domain.exhibition.mapper.ReviewRequestMapper;
import kr.co.arterium.domain.exhibition.mapper.ReviewResponseMapper;
import kr.co.arterium.domain.exhibition.repository.ReviewRepository;
import kr.co.arterium.domain.user.entity.UserEntity;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final PostService postService;


    // 리뷰 생성
    @Override
    @Transactional
    public Long save(ReviewRequestDTO reviewDto) {
        ReviewEntity review = ReviewRequestMapper.MAPPER.toEntity(reviewDto);       // entity -> dto
        PostDTO postDto = postService.findByPostId(reviewDto.getPostId());          // post dto 찾기
        PostEntity postEntity = PostMapper.MAPPER.toEntity(postDto);                // post dto -> post entity
        UserEntity userEntity = userService.findByUserId(reviewDto.getUserId()).get();  // user entity 찾기
        review.setPost(postEntity);
        review.setUser(userEntity);

        return reviewRepository.save(review).getId();
    }
    
    // 단일 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public ReviewResponseDTO findOneById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).get();
        ReviewResponseDTO reviewDTO = ReviewResponseMapper.MAPPER.toDTO(reviewEntity); // entity -> dto
        return reviewDTO;
    }

    
    // 전체 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> findReviews() {
        List<ReviewEntity> reviewEntityList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "id")); // 내림차순 정렬
        List<ReviewResponseDTO> reviews = ReviewResponseMapper.MAPPER.toDtoList(reviewEntityList); // entity list -> dto list
        return reviews;
    }
    
    // 특정 post에 대한 전체 리뷰 찾기
    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> findReviewsByPostId(Long postId) {
        List<ReviewEntity> reviewEntityList =  reviewRepository.findByPostIdOrderByIdDesc(postId);  // entity list
        List<ReviewResponseDTO> reviews = ReviewResponseMapper.MAPPER.toDtoList(reviewEntityList);  // -> dto list
        return reviews;
    }

    // 리뷰 수정
    @Transactional
    public void update(ReviewRequestDTO reviewDto, Long id){
        ReviewEntity originalEntity = reviewRepository.findById(id)     // 리뷰 id 검색하여 존재 여부 확인
                .orElseThrow(() -> {
                    return new IllegalStateException("리뷰 수정 실패");   // 없으면 수정 실패
                });

        ReviewEntity modifiedEntity = ReviewRequestMapper.MAPPER.toEntity(reviewDto);
        originalEntity.setContent(modifiedEntity.getContent());
        originalEntity.setRating(modifiedEntity.getRating());
    }

    // 리뷰 삭제
    @Transactional
    public void delete(Long id){
        ReviewEntity entity = reviewRepository.findById(id)             // 리뷰 id 검색하여 존재 여부 확인
                .orElseThrow(() -> {
                    return new IllegalStateException("리뷰 삭제 실패");   // 없으면 삭제 실패
                });

        reviewRepository.deleteById(id);
    }

}
