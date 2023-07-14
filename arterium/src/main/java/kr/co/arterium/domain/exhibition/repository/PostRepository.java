package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findById(Long id);
}
