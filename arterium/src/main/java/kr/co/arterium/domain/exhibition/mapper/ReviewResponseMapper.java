package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.ReviewResponseDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Mapper
public interface ReviewResponseMapper extends EntityMapper<ReviewResponseDTO, ReviewEntity> {

    ReviewResponseMapper MAPPER = Mappers.getMapper(ReviewResponseMapper.class);

    default ReviewResponseDTO toDTO(ReviewEntity entity) {
        Long id = entity.getId();
        Long postId = entity.getPost().getId();
        Long userId = entity.getUser().getId();
        String userNickname = entity.getUser().getNickname();
        String userProfileUrl = entity.getUser().getProfileUrl();
        String content = entity.getContent();
        LocalDateTime createdAt = entity.getCreatedAt();
        LocalDateTime updatedAt = entity.getUpdatedAt();
        int rating = entity.getRating();

        ReviewResponseDTO reviewDTO = new ReviewResponseDTO(id, postId, userId, userNickname, userProfileUrl, content, createdAt, updatedAt, rating);
        return reviewDTO;
    }


    @Override
    default ReviewEntity toEntity(final ReviewResponseDTO dto) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(dto.getId());
        reviewEntity.setContent(dto.getContent());
        reviewEntity.setRating(dto.getRating());
        reviewEntity.setCreatedAt(dto.getCreatedAt());
        reviewEntity.setUpdatedAt(dto.getUpdatedAt());
        return reviewEntity;
    }

    default List<ReviewResponseDTO> toDtoList(List<ReviewEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReviewResponseDTO> list = new ArrayList<ReviewResponseDTO>( entityList.size() );
        for ( ReviewEntity reviewEntity : entityList ) {
            list.add( toDTO( reviewEntity ) );
        }

        return list;
    }

    default List<ReviewEntity> toEntityList(List<ReviewResponseDTO> dtoList){
        if ( dtoList == null ) {
            return null;
        }

        List<ReviewEntity> list = new ArrayList<ReviewEntity>( dtoList.size() );
        for ( ReviewResponseDTO dto : dtoList ) {
            list.add( toEntity( dto ) );
        }

        return list;
    }

}
