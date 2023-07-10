package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExhibitionMapper extends EntityMapper<ExhibitionDTO, ExhibitionEntity> {

    ExhibitionMapper MAPPER = Mappers.getMapper(ExhibitionMapper.class);

    @Override
    ExhibitionEntity toEntity(final ExhibitionDTO dto);
}
