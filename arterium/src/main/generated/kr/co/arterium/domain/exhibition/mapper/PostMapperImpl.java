package kr.co.arterium.domain.exhibition.mapper;

import javax.annotation.processing.Generated;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.entity.PostEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T15:20:10+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class PostMapperImpl implements PostMapper {

    @Override
    public PostEntity toEntity(PostDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PostEntity.PostEntityBuilder postEntity = PostEntity.builder();

        postEntity.id( dto.getId() );
        postEntity.title( dto.getTitle() );
        postEntity.startDate( dto.getStartDate() );
        postEntity.endDate( dto.getEndDate() );
        postEntity.viewingTime( dto.getViewingTime() );
        postEntity.ageRestriction( dto.getAgeRestriction() );
        if ( dto.getPrice() != null ) {
            postEntity.price( dto.getPrice() );
        }
        postEntity.summary( dto.getSummary() );
        postEntity.description( dto.getDescription() );
        postEntity.originUrl( dto.getOriginUrl() );
        if ( dto.getIsEligibility() != null ) {
            postEntity.isEligibility( dto.getIsEligibility() );
        }
        postEntity.eligibilityDate( dto.getEligibilityDate() );

        return postEntity.build();
    }

    @Override
    public PostDTO toDTO(PostEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PostDTO.PostDTOBuilder postDTO = PostDTO.builder();

        postDTO.id( entity.getId() );
        postDTO.title( entity.getTitle() );
        postDTO.startDate( entity.getStartDate() );
        postDTO.endDate( entity.getEndDate() );
        postDTO.viewingTime( entity.getViewingTime() );
        postDTO.ageRestriction( entity.getAgeRestriction() );
        postDTO.price( entity.getPrice() );
        postDTO.summary( entity.getSummary() );
        postDTO.description( entity.getDescription() );
        postDTO.originUrl( entity.getOriginUrl() );
        postDTO.isEligibility( entity.getIsEligibility() );
        postDTO.eligibilityDate( entity.getEligibilityDate() );

        return postDTO.build();
    }
}
