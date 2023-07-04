package kr.co.arterium.domain.exhibition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostEntity {
    @Id //primary key
    @Column(name="id")
    private Long postId;
}
