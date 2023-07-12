package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.BookingLinkDTO;
import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingLinkMapper extends EntityMapper<BookingLinkDTO, BookingLinkEntity> {

    BookingLinkMapper MAPPER = Mappers.getMapper(BookingLinkMapper.class);

    @Override
    BookingLinkEntity toEntity(final BookingLinkDTO dto);

    @Override
    BookingLinkDTO toDTO(final BookingLinkEntity entity);
}
