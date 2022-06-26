package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "comments")
@EntityListeners(value = AuditingEntityListener.class)
public class CommentEntity extends BaseEntity{
    private String text;
}
