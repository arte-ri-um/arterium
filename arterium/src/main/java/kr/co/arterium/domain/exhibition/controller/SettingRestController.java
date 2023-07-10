package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.entity.BookingSiteEntity;
import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import kr.co.arterium.domain.exhibition.mapper.BookingSiteMapper;
import kr.co.arterium.domain.exhibition.mapper.ExhibitionMapper;
import kr.co.arterium.domain.exhibition.repository.BookingSiteRepository;
import kr.co.arterium.domain.exhibition.repository.ExhibitionRepository;
import kr.co.arterium.domain.exhibition.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SettingRestController{

    private final ExhibitionRepository exhibitionRepository;
    private final BookingSiteRepository bookingSiteRepository;
    private final FileService fileService;

    @GetMapping("/admin-page")
    public ModelAndView admin(ModelAndView modelAndView){
        modelAndView.setViewName("setting/settingPage");
        return modelAndView;
    }

    @GetMapping("/artpost/create-exhibition")
    public ModelAndView createExhibitionView(ModelAndView modelAndView){    //전시회 리스트 뷰로 이동
        List<ExhibitionEntity> exhibitions = exhibitionRepository.findAll();    // 전시회 리스트 가져오기
        modelAndView.addObject("exhibitions",exhibitions);  //전시회 리스트 model넣기
        modelAndView.setViewName("setting/createExhibitionView");   //전시회 view 보내기
        return modelAndView;
    }

    @PostMapping("/artpost/create-exhibition")
    @ResponseBody
    public ResponseEntity createExhibition(@RequestBody ExhibitionDTO exhibitionDTO){  // 전시회 리스트 더하기
        ExhibitionEntity entity = ExhibitionMapper.MAPPER.toEntity(exhibitionDTO);    //전시장 값 넣기
        // TODO 이미지를 정규 이미지DB로 옮겨야 함
        exhibitionRepository.save(entity);  //전시장 값 DB 저장
        List<ExhibitionEntity> exhibitions = exhibitionRepository.findAll();    // 전시회 리스트 가져오기
        return ResponseEntity.ok(exhibitions);  //전시장 값을 Entity값으로 반환
    }

    @GetMapping("/artpost/create-booking-site")
    public ModelAndView createBookingSiteView(ModelAndView modelAndView){   // 예약사이트 뷰로 이동
        List<BookingSiteEntity> bookingSites = bookingSiteRepository.findAll(); //예약 사이트 정보 가져오기
        modelAndView.addObject("bookingSites",bookingSites);    // 예약 사이트 리스트 model 전송
        modelAndView.setViewName("setting/createBookinSiteView");  //예약 사이트 view 전송
        return modelAndView;
    }

    @PostMapping("/artpost/create-booking-site")
    @ResponseBody
    public ResponseEntity createBookingSiteView(@RequestBody BookingSiteDTO bookingSiteDTO){  // 전시회 리스트 더하기
        BookingSiteEntity entity = BookingSiteMapper.MAPPER.toEntity(bookingSiteDTO);    //전시장 값 넣기
        bookingSiteRepository.save(entity);  //전시장 값 DB 저장
        List<BookingSiteEntity> bookingSites = bookingSiteRepository.findAll();    // 전시회 리스트 가져오기
        return ResponseEntity.ok(bookingSites);  //전시장 값을 Entity값으로 반환
    }

    @PostMapping("/upload-image")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestBody MultipartFile file){
        Map<String, Object> responseData = fileService.saveTempImage(file);
        return ResponseEntity.ok(responseData);
    }

}
