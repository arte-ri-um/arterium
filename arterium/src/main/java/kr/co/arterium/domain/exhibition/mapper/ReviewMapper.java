package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ReviewMapper extends EntityMapper<ReviewDTO, ReviewEntity> {

    ReviewMapper MAPPER = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO toDto(ReviewEntity entity);

    @Override
    default ReviewEntity toEntity(ReviewDTO dto){
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent(dto.getContent());
        reviewEntity.setRating(dto.getRating());
        return reviewEntity;
    }

}
