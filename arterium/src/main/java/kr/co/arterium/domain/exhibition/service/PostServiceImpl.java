package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
}
