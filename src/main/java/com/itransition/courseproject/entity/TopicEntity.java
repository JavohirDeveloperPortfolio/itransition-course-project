package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "topics")
@EntityListeners(value = AuditingEntityListener.class)
public class TopicEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
