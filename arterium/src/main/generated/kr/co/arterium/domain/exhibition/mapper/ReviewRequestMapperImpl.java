package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.ReviewRequestDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T15:20:10+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class ReviewRequestMapperImpl implements ReviewRequestMapper {

    @Override
    public ReviewRequestDTO toDTO(ReviewEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO();

        reviewRequestDTO.setContent( entity.getContent() );
        reviewRequestDTO.setRating( entity.getRating() );

        return reviewRequestDTO;
    }

    @Override
    public ReviewEntity toEntity(ReviewRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReviewEntity.ReviewEntityBuilder reviewEntity = ReviewEntity.builder();

        reviewEntity.content( dto.getContent() );
        reviewEntity.rating( dto.getRating() );

        return reviewEntity.build();
    }
}
