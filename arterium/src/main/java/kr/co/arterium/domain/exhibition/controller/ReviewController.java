package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.dto.ReviewDTO;
import kr.co.arterium.domain.exhibition.service.PostService;
import kr.co.arterium.domain.exhibition.service.ReviewServiceImpl;
import kr.co.arterium.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }
    
    // 리뷰 작성
    @GetMapping("/{postId}/review/create")
    public ModelAndView create(@PathVariable(name = "postId") Long postId, ModelAndView modelAndView) {
        modelAndView.addObject("post", postService.findByPostId(postId).get());
        Long userId = 1L; // 테스트용
        modelAndView.addObject("user", userService.findByUserId(userId).get()); // 테스트용
        modelAndView.setViewName("review/create");
        return modelAndView;
    }
    
    @PostMapping("/{postId}/review/create")
    public ModelAndView create(ReviewDTO reviewDTO, HttpSession session,
                               ModelAndView modelAndView,
                         @PathVariable(name = "postId") Long postId) {
        //Long id = (Long) session.getAttribute("id");
        Long userId = 1L; // 테스트용
        reviewService.create(reviewDTO, userId, postId);
        modelAndView.addObject("reviews", reviewService.findReviewsByPostId(postId));
        modelAndView.setViewName("review/list");
        return modelAndView;
    }

}
