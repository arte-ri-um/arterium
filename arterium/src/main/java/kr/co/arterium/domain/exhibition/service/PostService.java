package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    List<ExhibitionDTO> findExhibitionsList();

    List<BookingSiteDTO> findBookingSiteList();

    Long createPost(PostDTO postDTO);
}
