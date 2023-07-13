package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.*;
import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.exhibition.mapper.BookingLinkMapper;
import kr.co.arterium.domain.exhibition.mapper.BookingSiteMapper;
import kr.co.arterium.domain.exhibition.mapper.ExhibitionMapper;
import kr.co.arterium.domain.exhibition.mapper.PostMapper;
import kr.co.arterium.domain.exhibition.repository.BookingLinkRepository;
import kr.co.arterium.domain.exhibition.repository.BookingSiteRepository;
import kr.co.arterium.domain.exhibition.repository.ExhibitionRepository;
import kr.co.arterium.domain.exhibition.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final BookingSiteRepository bookingSiteRepository;
    private final BookingLinkRepository bookingLinkRepository;

    @Override
    public List<ExhibitionDTO> findExhibitionsList() {  //전시장 정보 전부 가져오기
        List<ExhibitionEntity> exhibitions = exhibitionRepository.findAll();    //db에서 전부 가져오기
        return exhibitions.stream()//매핑해서 return TODO null 에러 처리 해야 함
                .map(ExhibitionMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingSiteDTO> findBookingSiteList() {     // 예매 사이트 전부 정보 가져오기
        List<BookingSiteEntity> bookingSites = bookingSiteRepository.findAll(); //db에서 전부 가져오기
        return bookingSites.stream()    //매핑해서 return TODO null 에러 처리 해야 함
                .map(BookingSiteMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createPost(PostDTO postDTO) {   //포스트 생성하기
        //post등록
        PostEntity postEntity = PostMapper.MAPPER.toEntityWithDefault(postDTO);
        postRepository.save(postEntity);
        Long PostId = postEntity.getId();   // PostId 가져오기

        // 예약 사이트 링크 처리 - postId입력 후 entity 변환 처리
        List<BookingLinkDTO> bookingLinkDTOList = postDTO.getBookingLinks();
        List<BookingLinkEntity> bookingLinkEntityList = bookingLinkDTOList.stream()
                .peek(bookingLink -> bookingLink.setPostId(PostId))
                .map(BookingLinkMapper.MAPPER::toEntity)
                .collect(Collectors.toList());        
        bookingLinkRepository.saveAll(bookingLinkEntityList);// db저장

        return PostId;
    }

    @Override
    public PostViewDTO findPostById(Long postId) {  //postId로 페이지 정보 가져오기
        //포스트 정보 가져오기
        Optional<PostEntity> entity = postRepository.findById(postId);
        PostEntity postEntity = entity.isPresent() ? entity.get() : null; // TODO null이면 여기서 에러 처리 후 return

        PostViewDTO postDTO = PostMapper.MAPPER.toViewDTO(postEntity);//DTO로 변경하기

        //예약 사이트 링크 가져와서 PostDTO에 넣기
        List<BookingLinkEntity> bookingLinkEntities = bookingLinkRepository.findAllByPostId(postId);
        List<BookingLinkDTO> bookingDTOs = bookingLinkEntities != null
                ? bookingLinkEntities.stream()
                .map(bookingLink -> BookingLinkMapper.MAPPER.toDTO(bookingLink))
                .collect(Collectors.toList())
                : Collections.emptyList();
        postDTO.setBookingLinks(bookingDTOs);

        return postDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostEntity> findByPostId(Long id){
        return postRepository.findById(id);
    }

}
