package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.dto.PostViewDTO;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import java.util.Optional;

import java.util.List;

public interface PostService {
    List<ExhibitionDTO> findExhibitionsList();  //전시장 정보 전부 가져오기

    List<BookingSiteDTO> findBookingSiteList(); // 예매 사이트 전부 정보 가져오기

    Long createPost(PostDTO postDTO);//포스트 생성하기

    PostViewDTO findPostById(Long postId); //postId로 페이지 정보 가져오기

    Optional<PostEntity> findByPostId(Long id);
}
