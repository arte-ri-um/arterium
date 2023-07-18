package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.BookingLinkDTO;
import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T15:20:09+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class BookingLinkMapperImpl implements BookingLinkMapper {

    @Override
    public BookingLinkEntity toEntity(BookingLinkDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BookingLinkEntity.BookingLinkEntityBuilder bookingLinkEntity = BookingLinkEntity.builder();

        bookingLinkEntity.id( dto.getId() );
        bookingLinkEntity.postId( dto.getPostId() );
        bookingLinkEntity.siteId( dto.getSiteId() );
        bookingLinkEntity.bookingUrl( dto.getBookingUrl() );

        return bookingLinkEntity.build();
    }

    @Override
    public BookingLinkDTO toDTO(BookingLinkEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookingLinkDTO.BookingLinkDTOBuilder bookingLinkDTO = BookingLinkDTO.builder();

        bookingLinkDTO.id( entity.getId() );
        bookingLinkDTO.postId( entity.getPostId() );
        bookingLinkDTO.siteId( entity.getSiteId() );
        bookingLinkDTO.bookingUrl( entity.getBookingUrl() );

        return bookingLinkDTO.build();
    }
}
