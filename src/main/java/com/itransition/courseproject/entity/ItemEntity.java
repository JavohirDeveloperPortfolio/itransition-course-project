package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "items")
@EntityListeners(value = AuditingEntityListener.class)
public class ItemEntity extends BaseEntity{
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<TagEntity> tags;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CommentEntity> comments;

    @ManyToOne
    private CollectionEntity collection;
}
