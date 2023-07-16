package kr.co.arterium.domain.user.entity;

/*
인가에 필요한 테이블 생성 'authority'
컬럼은 authority_name varchar(50) 하나
manytomany로 user 테이블과 연결
*/

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
@NoArgsConstructor
public class AuthorityEntity { // 권한, role 없애기??

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @Builder
    public AuthorityEntity(String authorityName) {
        this.authorityName = authorityName;
    }


}
