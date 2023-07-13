package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-12T15:52:56+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toDTO(ReviewEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String content = null;
        int rating = 0;

        content = entity.getContent();
        rating = entity.getRating();

        ReviewDTO reviewDTO = new ReviewDTO( content, rating );

        return reviewDTO;
    }

    @Override
    public ReviewDTO toDto(ReviewEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String content = null;
        int rating = 0;

        content = entity.getContent();
        rating = entity.getRating();

        ReviewDTO reviewDTO = new ReviewDTO( content, rating );

        return reviewDTO;
    }
}
