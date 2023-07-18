package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T23:16:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class ExhibitionMapperImpl implements ExhibitionMapper {

    @Override
    public ExhibitionDTO toDTO(ExhibitionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ExhibitionDTO.ExhibitionDTOBuilder exhibitionDTO = ExhibitionDTO.builder();

        exhibitionDTO.id( entity.getId() );
        exhibitionDTO.name( entity.getName() );
        exhibitionDTO.location( entity.getLocation() );
        exhibitionDTO.holidayInfo( entity.getHolidayInfo() );
        exhibitionDTO.discountInfo( entity.getDiscountInfo() );
        exhibitionDTO.notice( entity.getNotice() );
        exhibitionDTO.siteUrl( entity.getSiteUrl() );

        return exhibitionDTO.build();
    }

    @Override
    public ExhibitionEntity toEntity(ExhibitionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ExhibitionEntity.ExhibitionEntityBuilder exhibitionEntity = ExhibitionEntity.builder();

        exhibitionEntity.id( dto.getId() );
        exhibitionEntity.name( dto.getName() );
        exhibitionEntity.location( dto.getLocation() );
        exhibitionEntity.holidayInfo( dto.getHolidayInfo() );
        exhibitionEntity.discountInfo( dto.getDiscountInfo() );
        exhibitionEntity.notice( dto.getNotice() );
        exhibitionEntity.siteUrl( dto.getSiteUrl() );

        return exhibitionEntity.build();
    }
}
