package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.domain.exhibition.entity.PostEntity;

import java.util.Optional;

public interface PostService {
    Optional<PostEntity> findById(Long id);

}
