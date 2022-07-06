package com.itransition.courseproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "collections")
@NoArgsConstructor
@Getter
@Setter
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

    public CollectionEntity(String name, String description, TopicEntity topicId, AttachmentEntity attachmentId) {
        super();
        this.name = name;
        this.description = description;
        this.topic = topicId;
        this.attachment = attachmentId;
    }
}
