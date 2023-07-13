package kr.co.arterium.domain.exhibition.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "post")
public class PostEntity {
    @Id //primary key
    @Column(name="id")
    private Long id;

}
