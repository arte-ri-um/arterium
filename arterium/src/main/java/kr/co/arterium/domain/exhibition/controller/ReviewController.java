package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.ReviewRequestDTO;
import kr.co.arterium.domain.exhibition.dto.ReviewResponseDTO;
import kr.co.arterium.domain.exhibition.service.PostService;
import kr.co.arterium.domain.exhibition.service.ReviewService;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artpost")
public class ReviewController {

    private final ReviewService reviewService;
    private final PostService postService;
    private final UserService userService;

    // 전체 리뷰 보기 (대상 전시 X)
    @GetMapping("/reviews")
    public ModelAndView all(ModelAndView modelAndView) {
        modelAndView.addObject("reviews", reviewService.findReviews());
        modelAndView.setViewName("review/all");
        return modelAndView;
    }

    // 전체 리뷰 보기 (대상 전시 O)
    @GetMapping("/post/{postId}/reviews")
    public ModelAndView list(@PathVariable(name = "postId") Long postId, ModelAndView modelAndView) {
        modelAndView.addObject("postDTO", postService.findPostById(postId));
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));

        Long userId = 1L;
        modelAndView.addObject("user", userService.findByUserId(userId).get()); // 테스트용
        modelAndView.setViewName("review/list");
        return modelAndView;
    }

    // 리뷰 작성
    @PostMapping("/post/{postId}/review/create")
    public ResponseEntity<?> create(@PathVariable(name = "postId") Long postId,
                                    ReviewRequestDTO reviewRequestDTO) {
        reviewService.save(reviewRequestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/artpost/post/" + postId));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }


    // 리뷰 수정
    @GetMapping("/post/{postId}/review/{id}/update")
    public ModelAndView update(@PathVariable(name = "postId") Long postId,
                               @PathVariable(name = "id") Long id,
                               HttpServletRequest request,
                               ModelAndView modelAndView) {
        ReviewResponseDTO review = reviewService.findOneById(id);
//        HttpSession session = request.getSession();
//        session.setAttribute("id", 1L);
        //Long userId = (Long) session.getAttribute("id");
        modelAndView.addObject("postDTO", postService.findPostById(postId));
        modelAndView.addObject("user", userService.findByUserId(1L).get()); // 테스트용
        modelAndView.addObject("review", review);
        modelAndView.setViewName("review/update");
        return modelAndView;
    }

    @PostMapping("/post/{postId}/review/{id}/update")
    public ResponseEntity<?> update(ReviewRequestDTO reviewDTO,
                                    @PathVariable(name = "postId") Long postId,
                                    @PathVariable(name = "id") Long id) {
        reviewService.update(reviewDTO, id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/artpost/post/" + postId));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // 리뷰 삭제
    @RequestMapping("/post/{postId}/review/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "postId") Long postId,
                                    @PathVariable(name = "id") Long id) {
        reviewService.delete(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/artpost/post/" + postId));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
