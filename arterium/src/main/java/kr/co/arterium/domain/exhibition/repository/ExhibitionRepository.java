package kr.co.arterium.domain.exhibition.repository;

import kr.co.arterium.domain.exhibition.entity.ExhibitionEntity;
import kr.co.arterium.domain.exhibition.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<ExhibitionEntity, Long> {
}
