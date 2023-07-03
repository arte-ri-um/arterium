package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

}
