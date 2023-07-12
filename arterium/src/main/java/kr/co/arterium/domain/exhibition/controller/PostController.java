package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artpost")
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public ModelAndView postView(ModelAndView modelAndView){
        //전시장 정보 가져오기 TODO 장소별로 가져오기
        List<ExhibitionDTO> exhibitions = postService.findExhibitionsList();
        modelAndView.addObject("exhibitions",exhibitions);

        //예매사이트 정보 가져오기
        List<BookingSiteDTO> bookingsites = postService.findBookingSiteList();
        modelAndView.addObject("bookingSites",bookingsites);
        modelAndView.setViewName("exhibition/post");
        return modelAndView;
    }

    @PostMapping("/create-post")
    public ResponseEntity<Map<String,Long>> createPost(@RequestBody PostDTO postDTO ){
        Map<String,Long> response = new HashMap<>();
        Long postId = postService.createPost(postDTO);
        response.put("postId",postId);
        return ResponseEntity.ok(response);
    }


}
