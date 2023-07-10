package kr.co.arterium.domain.exhibition.mapper;

/**
 * EntityMapper 인터페이스는 DTO(D)와 Entity(E) 간의 변환을 처리하는 메서드를 정의합니다.
 */
public interface EntityMapper<D, E> {

    /**
     * DTO를 Entity로 변환합니다.
     *
     * @param dto 변환할 DTO 객체
     * @return 변환된 Entity 객체
     */
    E toEntity(final D dto);

    /**
     * Entity를 DTO로 변환합니다.
     *
     * @param entity 변환할 Entity 객체
     * @return 변환된 DTO 객체
     */
    D toDTO(final E entity);
}
