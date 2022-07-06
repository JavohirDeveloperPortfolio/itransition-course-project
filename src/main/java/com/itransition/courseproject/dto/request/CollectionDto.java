package com.itransition.courseproject.dto.request;

import com.itransition.courseproject.entity.AttachmentEntity;
import com.itransition.courseproject.entity.TopicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {
    private String name;
    private String description;
    private String topicId;
    private String attachmentId;
}
