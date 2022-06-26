package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "items_data")
@EntityListeners(value = AuditingEntityListener.class)
public class DataEntity extends BaseEntity{
    private String value;
    @ManyToMany
    private Set<FieldEntity> field;
}
