package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "attachments")
@EntityListeners(value = AuditingEntityListener.class)
public class AttachmentEntity extends BaseEntity{
    private String name;
    private long size;
    private String type;
}
