package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T15:20:10+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class BookingSiteMapperImpl implements BookingSiteMapper {

    @Override
    public BookingSiteEntity toEntity(BookingSiteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BookingSiteEntity.BookingSiteEntityBuilder bookingSiteEntity = BookingSiteEntity.builder();

        bookingSiteEntity.id( dto.getId() );
        bookingSiteEntity.name( dto.getName() );
        bookingSiteEntity.siteUrl( dto.getSiteUrl() );
        bookingSiteEntity.iconUrl( dto.getIconUrl() );

        return bookingSiteEntity.build();
    }

    @Override
    public BookingSiteDTO toDTO(BookingSiteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookingSiteDTO.BookingSiteDTOBuilder bookingSiteDTO = BookingSiteDTO.builder();

        bookingSiteDTO.id( entity.getId() );
        bookingSiteDTO.name( entity.getName() );
        bookingSiteDTO.siteUrl( entity.getSiteUrl() );
        bookingSiteDTO.iconUrl( entity.getIconUrl() );

        return bookingSiteDTO.build();
    }
}
