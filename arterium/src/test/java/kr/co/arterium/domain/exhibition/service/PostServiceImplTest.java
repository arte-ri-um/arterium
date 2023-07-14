package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.BookingLinkDTO;
import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.dto.PostViewDTO;
import kr.co.arterium.domain.exhibition.entity.BookingLinkEntity;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import kr.co.arterium.domain.exhibition.mapper.BookingLinkMapper;
import kr.co.arterium.domain.exhibition.mapper.BookingSiteMapper;
import kr.co.arterium.domain.exhibition.mapper.PostMapper;
import kr.co.arterium.domain.exhibition.repository.BookingLinkRepository;
import kr.co.arterium.domain.exhibition.repository.BookingSiteRepository;
import kr.co.arterium.domain.exhibition.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@SpringBootTest
@Rollback(true) //rollback 꼭 넣어주기, id 예측이 힘들어짐
class PostServiceImplTest {

    @Autowired
    private BookingSiteRepository bookingSiteRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BookingLinkRepository bookingLinkRepository;

    @Test
    void findBookingSiteList() {    //예매처 등록 확인용
        List<BookingSiteEntity> bookingSites = bookingSiteRepository.findAll();
        List<BookingSiteDTO> bookingSitesDTO = bookingSites.stream()
                .map(BookingSiteMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        bookingSitesDTO.stream()
                .forEach(System.out::println);
    }

    @Test
    @Transactional
    void createPost() { // 포스트 등록 확인용

        //예제 데이터 생성
        List<BookingLinkDTO> bookingLinkList = new ArrayList<>();

        BookingLinkDTO bookingLinkDTO1 = BookingLinkDTO.builder()
                .postId(null)
                .siteId(1L)
                .bookingUrl("https://google.com")
                .build();

        BookingLinkDTO bookingLinkDTO2 = BookingLinkDTO.builder()
                .postId(null)
                .siteId(2L)
                .bookingUrl("https://naver.com")
                .build();
        BookingLinkDTO bookingLinkDTO3 = BookingLinkDTO.builder()
                .postId(null)
                .siteId(3L)
                .bookingUrl("https://daum.com")
                .build();
        bookingLinkList.add(bookingLinkDTO1);
        bookingLinkList.add(bookingLinkDTO2);
        bookingLinkList.add(bookingLinkDTO3);

        PostDTO postDTO = PostDTO.builder()
                .userId(1L)
                .exhibitionId(2L)
                .startDate(LocalDate.of(2023, 7, 1))
                .endDate(LocalDate.of(2023, 7, 31))
                .viewingTime("10:00 AM - 5:00 PM")
                .ageRestriction("18+")
                .price(10000)
                .summary("Art Exhibition")
                .description("This is an art exhibition showcasing various artworks.")
                .originUrl("http://example.com/exhibition")
                .isEligibility(1)
                .eligibilityDate(LocalDate.of(2023, 6, 1))
                .bookingLinks(bookingLinkList)
                .build();

        //Post등록
        PostEntity postEntity = PostMapper.MAPPER.toEntityWithDefault(postDTO);
        postRepository.save(postEntity);

        Long postId = postEntity.getId();

        // 예약 사이트 링크 처리
        // postId입력 후 entity 변환 처리
        List<BookingLinkDTO> bookingLinkDTOLists = postDTO.getBookingLinks();
        List<BookingLinkEntity> bookingLinkEntityList = bookingLinkDTOLists.stream()
                .peek(bookingLink -> bookingLink.setPostId(postId))
                .map(BookingLinkMapper.MAPPER::toEntity)
                .collect(Collectors.toList());
        // db저장
        bookingLinkRepository.saveAll(bookingLinkEntityList);

        System.out.println("postId = " + postId);

    }

    @Test
    @Transactional
    public void findPostById() {    // 포스트 view 정조 가져오기
        Long postId = 10L;
        Optional<PostEntity> entity = postRepository.findById(postId);
        PostEntity postEntity = entity.isPresent() ? entity.get() : null;
        PostViewDTO postDTO = PostMapper.MAPPER.toViewDTO(postEntity);
        List<BookingLinkEntity> bookingLinkEntities = bookingLinkRepository.findAllByPostId(11L);
        List<BookingLinkDTO> bookingDTOs = bookingLinkEntities != null
                ? bookingLinkEntities.stream()
                .map(bookingLink -> BookingLinkMapper.MAPPER.toDTO(bookingLink))
                .collect(Collectors.toList())
                : Collections.emptyList();
        postDTO.setBookingLinks(bookingDTOs);
        System.out.println(postDTO.getBookingLinks().toString());
    }

}