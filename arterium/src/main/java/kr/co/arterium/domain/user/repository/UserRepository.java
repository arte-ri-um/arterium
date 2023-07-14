package kr.co.arterium.domain.user.repository;

import kr.co.arterium.domain.user.dto.UserDTO;
import kr.co.arterium.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    // authorities를 함께 로딩하기 위해 attributePaths를 지정
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findByEmail(String email);
    @Override
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findById(Long aLong);
    @Override
    @EntityGraph(attributePaths = "authorities")
    List<UserEntity> findAllById(Iterable<Long> longs);

/*    Optional<UserEntity> findByEmail(String email);

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Override
    List<UserEntity> findAllById(Iterable<Long> longs);*/
}

