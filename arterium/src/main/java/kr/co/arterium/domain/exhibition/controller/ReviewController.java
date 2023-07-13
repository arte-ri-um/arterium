package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.entity.ReviewEntity;
import kr.co.arterium.domain.exhibition.service.PostService;
import kr.co.arterium.domain.exhibition.service.ReviewServiceImpl;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewServiceImpl reviewService;
    private final PostService postService;
    private final UserService userService;

    // 전체 리뷰 보기 (대상 전시 X)
    @GetMapping("/reviews")
    public ModelAndView all(ModelAndView modelAndView){
        modelAndView.addObject("reviews", reviewService.findReviews());
        modelAndView.setViewName("review/all");
        return modelAndView;
    }

    // 전체 리뷰 보기 (대상 전시 O)
    @GetMapping("/{postId}/reviews")
    public ModelAndView list(@PathVariable(name = "postId") Long postId, ModelAndView modelAndView){
        modelAndView.addObject("postId", postId);
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }
    
    // 리뷰 작성
    @GetMapping("/{postId}/review/create")
    public ModelAndView create(@PathVariable(name = "postId") Long postId,
                               HttpServletRequest request,
                               ModelAndView modelAndView) {
        //modelAndView.addObject("post", postService.findByPostId(postId).get());
        modelAndView.addObject("postId", postId);
        HttpSession session = request.getSession();
        session.setAttribute("id", 1L);
        //Long userId = (Long) session.getAttribute("id");
        Long userId = 1L;
        modelAndView.addObject("user", userService.findByUserId(userId).get()); // 테스트용
        modelAndView.setViewName("review/create");
        return modelAndView;
    }
    
    @PostMapping("/{postId}/review/create")
    public ModelAndView create(ReviewDTO reviewDTO, HttpServletRequest request,
                               ModelAndView modelAndView,
                         @PathVariable(name = "postId") Long postId) {
        HttpSession session = request.getSession();
        session.setAttribute("id", 1L);
        //Long userId = (Long) session.getAttribute("id");
        Long userId = 1L;
        reviewService.create(reviewDTO, userId, postId);
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }


    // 리뷰 수정
    @GetMapping("/{postId}/review/{id}/update")
    public ModelAndView update(@PathVariable(name = "postId") Long postId,
                               @PathVariable(name = "id") Long id,
                               HttpServletRequest request,
                               ModelAndView modelAndView){
        ReviewEntity review = reviewService.findOneById(id).get();
        HttpSession session = request.getSession();
        session.setAttribute("id", 1L);
        //Long userId = (Long) session.getAttribute("id");
        Long userId = 1L;
        modelAndView.addObject("user", userService.findByUserId(userId).get()); // 테스트용
        modelAndView.addObject("review", review);
        modelAndView.setViewName("review/update");
        return modelAndView;
    }

    @PostMapping("/{postId}/review/{id}/update")
    public ModelAndView update(ReviewDTO reviewDTO,
                               @PathVariable(name = "postId") Long postId,
                               @PathVariable(name = "id") Long id,
                               ModelAndView modelAndView){
        reviewService.update(reviewDTO, id);
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }

    // 리뷰 삭제
    @GetMapping("/{postId}/review/{id}/delete")
    public ModelAndView delete(@PathVariable(name = "postId") Long postId,
                               @PathVariable(name = "id") Long id,
                               ModelAndView modelAndView){
        reviewService.delete(id);
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }

    // 리뷰 조회
    //@GetMapping("/reviews/search")

}
