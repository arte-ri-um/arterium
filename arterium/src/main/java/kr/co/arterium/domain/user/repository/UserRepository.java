package kr.co.arterium.domain.user.repository;

import kr.co.arterium.domain.user.dto.UserDTO;
import kr.co.arterium.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Override
    List<UserEntity> findAllById(Iterable<Long> longs);
}
