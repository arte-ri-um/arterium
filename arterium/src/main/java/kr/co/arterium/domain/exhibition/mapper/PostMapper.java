package kr.co.arterium.domain.exhibition.mapper;

import kr.co.arterium.domain.exhibition.dto.*;
import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper extends EntityMapper<PostDTO, PostEntity> {

    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "user", source = "postDTO.userId", qualifiedByName = "toUserEntity")
    @Mapping(target = "exhibition", source = "postDTO.exhibitionId", qualifiedByName = "toExhibitionEntity")
    @Mapping(target = "bookingLinks", source = "postDTO.bookingLinks", qualifiedByName = "toBookingLinkEntities")
    @Override
    PostEntity toEntity(final PostDTO postDTO); // 이상하게도 이걸 update에 사용 

    @Named("toUserEntity")
    default UserEntity toUserEntity(Long userId) {  //userEntity에 userID 넣어주기
        UserEntity userEntity = UserEntity.UserBuilder().id(userId).build();
        return userEntity;
    }

    @Named("toExhibitionEntity")
    default ExhibitionEntity toExhibitionEntity(Long exihibitionId) {   //ExhibitionEntity에 exhibitionID 넣어주기
        ExhibitionEntity exhibitionEntity = ExhibitionEntity.builder().id(exihibitionId).build();
        return exhibitionEntity;
    }

    @Named("toBookingLinkEntities")
    default List<BookingLinkEntity> toBookingLinkEntities(List<BookingLinkDTO> bookingLinkDTOList) {    // BookingLinksEntity에 dto 변환해서 넣어주기
        List<BookingLinkEntity> bookingLinkEntityList = bookingLinkDTOList.stream()
                .map(dto -> {   // bookingLinkEntity의 Mapper를 바꿔줘야 해서 그냥 새로 build함 
                    PostEntity postEntity = PostEntity.builder().id(dto.getPostId()).build();
                    return BookingLinkEntity.builder()
                            .post(postEntity)
                            .siteId(dto.getSiteId())
                            .bookingUrl(dto.getBookingUrl())
                            .build();
                })
                .collect(Collectors.toList());
        return bookingLinkEntityList;
    }

    default PostEntity toEntityWithDefault(PostDTO postDTO){    // dto -> entity, 이상하지만 이걸 create에 사용

        //default값 설정
        String viewingTime = postDTO.getViewingTime() != null ? postDTO.getViewingTime() : "전시장 시간 참조";
        String ageRestriction = postDTO.getAgeRestriction() != null ? postDTO.getAgeRestriction() : "전체관람가";
        int price = postDTO.getPrice() != null ? postDTO.getPrice() : 0;
        int isEligibility = postDTO.getIsEligibility() != null ? postDTO.getIsEligibility() : 0;

        // 예약 사이트 링크 처리
        List<BookingLinkDTO> bookingLinkDTOList = postDTO.getBookingLinks();
        List<BookingLinkEntity> bookingLinkEntityList = bookingLinkDTOList.stream()
                .map(BookingLinkMapper.MAPPER::toEntity)
                .collect(Collectors.toList());
        //entity 설정
        return PostEntity.builder()
                .user(UserEntity.UserBuilder().id(postDTO.getUserId()).build())
                .title(postDTO.getTitle())
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
                .regDate(LocalDateTime.now())
                .bookingLinks(bookingLinkEntityList)
                .build();
    }

    default PostViewDTO toViewDTO(PostEntity entity){   //entity -> dto
        ExhibitionDTO exhibitionDTO = ExhibitionMapper.MAPPER.toDTO(entity.getExhibition());
        List<BookingLinkDTO> bookingLinks = entity.getBookingLinks().stream()
                .map(BookingLinkMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return PostViewDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
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
                .regDate(entity.getRegDate())
                .bookingLinks(bookingLinks)
                .build();
    }
}
