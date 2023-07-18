package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.ReviewRequestDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewRequestMapper extends EntityMapper<ReviewRequestDTO, ReviewEntity> {

    ReviewRequestMapper MAPPER = Mappers.getMapper(ReviewRequestMapper.class);

    ReviewEntity toEntity(final ReviewRequestDTO dto);

}
