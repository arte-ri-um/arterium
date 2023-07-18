package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.BookingSiteDTO;
import kr.co.arterium.domain.exhibition.dto.ExhibitionDTO;
import kr.co.arterium.domain.exhibition.dto.PostDTO;
import kr.co.arterium.domain.exhibition.dto.PostViewDTO;
import kr.co.arterium.domain.exhibition.service.PostService;
import kr.co.arterium.domain.exhibition.service.ReviewService;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artpost")
public class PostController {
    private final PostService postService;
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/create-post")
    public ModelAndView postCreateView(ModelAndView modelAndView){    // 포스트 생성 뷰
        //전시장 정보 가져오기 TODO 장소별로 가져오기
        List<ExhibitionDTO> exhibitions = postService.findExhibitionsList();
        modelAndView.addObject("exhibitions",exhibitions);

        //예매사이트 정보 가져오기
        List<BookingSiteDTO> bookingsites = postService.findBookingSiteList();
        modelAndView.addObject("bookingSites",bookingsites);

        //페이지 이름 셋팅
        modelAndView.setViewName("exhibition/createPostView");
        return modelAndView;
    }

    @PostMapping("/create-post")
    public ResponseEntity<Map<String,String>> createPost(@RequestBody PostDTO postDTO ){    // 포스트 생성
        postDTO.setUserId(1L);  // TODO 아직 user 연동 안해서 세션해도 되는지 모르겠음

        Long postId = postService.createPost(postDTO);  //포스트 정보 db 저장

        //리다이렉트 URL을 응답에 포함시키기
        Map<String,String> response = new HashMap<>();
        response.put("redirectUrl","/artpost/post/" + postId);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/post/{postId}")
    public ModelAndView showPostById(@PathVariable("postId")Long postId, ModelAndView modelAndView){    // 포스트 id로 정보 보여주기
        PostViewDTO onePostDTO = postService.findPostById(postId);  // 포스트 id로 정보 가져오기
        modelAndView.addObject("postDTO",onePostDTO);   //모델 입력
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId)); // 포스트 id로 리뷰 가져오기
        modelAndView.addObject("user", userService.findByUserId(1L).get()); // 테스트용
        modelAndView.setViewName("exhibition/postView");    //페이지 셋팅
        return modelAndView;
    }

    @PostMapping("/post/{postId}/delete")
    public ResponseEntity<Map<String,String>> deletePostById(@PathVariable("postId")Long postId){ //포스트 삭제 - 전체 리스트가 없어서 create로 되돌아감
        postService.deleteById(postId);
        //리다이렉트 URL을 응답에 포함시키기
        Map<String,String> response = new HashMap<>();
        response.put("redirectUrl","/artpost/create-post");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{postId}/edit")
    public ModelAndView modifyPostViewById(@PathVariable("postId")Long postId,ModelAndView modelAndView){   // 포스트 수정 view
        //post 정보 가져오기
        PostViewDTO onePostDTO = postService.findPostById(postId);
        modelAndView.addObject("postDTO",onePostDTO);

        //전시장 정보 가져오기 TODO 장소별로 가져오기
        List<ExhibitionDTO> exhibitions = postService.findExhibitionsList();
        modelAndView.addObject("exhibitions",exhibitions);

        //예매사이트 정보 가져오기
        List<BookingSiteDTO> bookingsites = postService.findBookingSiteList();
        modelAndView.addObject("bookingSites",bookingsites);

        modelAndView.setViewName("exhibition/createPostView");    //페이지 셋팅
        return modelAndView;
    }

    @PostMapping("/post/{postId}/edit")
    public ResponseEntity<Map<String,String>> modifyPostById(
            @PathVariable("postId")Long postId,@RequestBody PostDTO postDTO, ModelAndView modelAndView){    // 수정 저장
        postDTO.setUserId(1L);  // TODO 아직 user 연동 안해서 세션해도 되는지 모르겠음
        postService.modifyById(postDTO);    //변경내용저장

        //리다이렉트 URL을 응답에 포함시키기
        Map<String,String> response = new HashMap<>();
        response.put("redirectUrl","/artpost/post/" + postId);

        return ResponseEntity.ok(response);
    }
}
