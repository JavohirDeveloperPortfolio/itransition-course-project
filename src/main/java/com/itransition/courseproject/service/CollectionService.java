package com.itransition.courseproject.service;

import com.itransition.courseproject.dto.request.CollectionDto;
import com.itransition.courseproject.dto.request.FieldDto;
import com.itransition.courseproject.dto.request.TopicDto;
import com.itransition.courseproject.dto.response.CollectionResponse;
import com.itransition.courseproject.entity.AttachmentEntity;
import com.itransition.courseproject.entity.CollectionEntity;
import com.itransition.courseproject.entity.FieldEntity;
import com.itransition.courseproject.entity.TopicEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CollectionService {
    Boolean checkCollectionTopicName(String name);
    TopicEntity addTopic(TopicDto topicDto);
    List<TopicEntity> getTopicAll();
    TopicEntity getTopic(UUID id);
    List<TopicEntity> getTopic(String name);
    TopicEntity editTopic(UUID id, TopicDto topicDto);
    void deleteTopic(UUID id);

    AttachmentEntity addAttachment(MultipartFile file);
    AttachmentEntity getAttachment(UUID id);

    CollectionEntity addCollection(CollectionDto collectionDto);
    CollectionEntity editCollection(CollectionDto collectionDto);
    CollectionEntity deleteCollection(UUID id);
    List<CollectionResponse> getCollectionAll();
    CollectionEntity getCollection(UUID id);
    List<CollectionResponse> getCollectionByUserId(String email);
    List<CollectionResponse> getCollection(String name);
//    List<CollectionResponse> getCollectionByTopicName(String name);

    FieldEntity addField(FieldDto field);
    FieldEntity deleteField(UUID id);
    FieldEntity editField(FieldDto field, UUID id);
    FieldEntity getFieldById(UUID id);
    List<FieldEntity> getFieldAll();
}
