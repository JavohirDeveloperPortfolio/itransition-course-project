package com.itransition.courseproject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "collections")
@EntityListeners(value = AuditingEntityListener.class)
public class CollectionEntity extends BaseEntity{
    private String name;
    private String description;

    @OneToOne
    private TopicEntity topic;

    @OneToOne
    private AttachmentEntity attachment;

    @ManyToMany
    @JoinTable(
            name = "collections_field",
            joinColumns = @JoinColumn(name = "collections_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "collections_field_id",referencedColumnName = "id"))
    private Set<FieldEntity> field;
}
