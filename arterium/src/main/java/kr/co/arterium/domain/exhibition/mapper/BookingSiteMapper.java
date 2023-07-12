package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingSiteMapper extends EntityMapper<BookingSiteDTO, BookingSiteEntity> {

    BookingSiteMapper MAPPER = Mappers.getMapper(BookingSiteMapper.class);

    @Override
    BookingSiteEntity toEntity(final BookingSiteDTO dto);   // dto -> entity

    @Override
    BookingSiteDTO toDTO(final BookingSiteEntity entity);   //entity -> dto
}
