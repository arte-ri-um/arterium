package kr.co.arterium.domain.exhibition.dto;

import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BookingSiteDTO {
    private Long id;
    private String name;
    private String siteUrl;
    private String iconUrl;
}

