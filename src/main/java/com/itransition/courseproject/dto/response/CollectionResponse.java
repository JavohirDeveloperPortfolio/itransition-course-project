package com.itransition.courseproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResponse {
    private String name;
    private String description;
    private UUID topicId;
    private UUID attachmentId;
}
