package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.dto.BookingLinkDTO;
import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final BookingSiteRepository bookingSiteRepository;
    private final BookingLinkRepository bookingLinkRepository;

    @Override
    public List<ExhibitionDTO> findExhibitionsList() {
        List<ExhibitionEntity> exhibitions = exhibitionRepository.findAll();
        return exhibitions.stream()
                .map(ExhibitionMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingSiteDTO> findBookingSiteList() {
        List<BookingSiteEntity> bookingSites = bookingSiteRepository.findAll();
        return bookingSites.stream()
                .map(BookingSiteMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createPost(PostDTO postDTO) {
        //post등록
        PostEntity postEntity = PostMapper.MAPPER.toEntity(postDTO);
        postRepository.save(postEntity);

        Long PostId = postEntity.getId();

        // 예약 사이트 링크 처리
        // postId입력 후 entity 변환 처리
        List<BookingLinkDTO> bookingLinkDTOList = postDTO.getBookingLinks();
        List<BookingLinkEntity> bookingLinkEntityList = bookingLinkDTOList.stream()
                .peek(bookingLink -> bookingLink.setPostId(PostId))
                .map(BookingLinkMapper.MAPPER::toEntity)
                .collect(Collectors.toList());
        // db저장
        bookingLinkRepository.saveAll(bookingLinkEntityList);

        return PostId;
    }
}
