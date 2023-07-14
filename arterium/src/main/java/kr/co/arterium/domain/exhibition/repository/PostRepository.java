package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {   //포스트 repository
    PostEntity findAllById(Long postId);    // postId로
}
