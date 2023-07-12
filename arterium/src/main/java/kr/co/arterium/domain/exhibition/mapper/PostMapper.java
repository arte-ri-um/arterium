package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.dto.PostViewDTO;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper extends EntityMapper<PostDTO, PostEntity> {

    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    default PostEntity toEntityWithDefault(PostDTO postDTO){    // dto -> entity

        //default값 설정
        String viewingTime = postDTO.getViewingTime() != null ? postDTO.getViewingTime() : "전시장 시간 참조";
        String ageRestriction = postDTO.getAgeRestriction() != null ? postDTO.getAgeRestriction() : "전체관람가";
        int price = postDTO.getPrice() != null ? postDTO.getPrice() : 0;
        int isEligibility = postDTO.getIsEligibility() != null ? postDTO.getIsEligibility() : 0;

        //entity 설정
        return PostEntity.builder()
                .user(UserEntity.UserBuilder().id(postDTO.getUserId()).build())
                .exhibition(ExhibitionEntity.builder().id(postDTO.getExhibitionId()).build())
                .startDate(postDTO.getStartDate())
                .endDate(postDTO.getEndDate())
                .viewingTime(viewingTime)
                .ageRestriction(ageRestriction)
                .price(price)
                .summary(postDTO.getSummary())
                .description(postDTO.getDescription())
                .originUrl(postDTO.getOriginUrl())
                .isEligibility(isEligibility)
                .eligibilityDate(postDTO.getEligibilityDate())
                .regDate(LocalDate.now())
                .build();
    }

    default PostViewDTO toViewDTO(PostEntity entity){   //entity -> dto

        ExhibitionDTO exhibitionDTO = ExhibitionMapper.MAPPER.toDTO(entity.getExhibition());
        return PostViewDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userNickname(entity.getUser().getNickname())
                .exhibitionDTO(exhibitionDTO)
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .viewingTime(entity.getViewingTime())
                .ageRestriction(entity.getAgeRestriction())
                .price(entity.getPrice())
                .summary(entity.getSummary())
                .description(entity.getDescription())
                .originUrl(entity.getOriginUrl())
                .isEligibility(entity.getIsEligibility())
                .eligibilityDate(entity.getEligibilityDate())
                .build();
    }

}
