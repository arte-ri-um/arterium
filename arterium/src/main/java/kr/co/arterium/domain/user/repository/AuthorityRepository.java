package kr.co.arterium.domain.user.repository;

import kr.co.arterium.domain.user.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {
    Optional<AuthorityEntity> findByAuthorityName(String authorityName);
}
