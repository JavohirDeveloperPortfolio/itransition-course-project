package com.itransition.courseproject.service.impl;

import com.itransition.courseproject.dto.request.CollectionDto;
import com.itransition.courseproject.dto.request.FieldDto;
import com.itransition.courseproject.dto.request.TopicDto;
import com.itransition.courseproject.dto.response.CollectionResponse;
import com.itransition.courseproject.entity.AttachmentEntity;
import com.itransition.courseproject.entity.CollectionEntity;
import com.itransition.courseproject.entity.FieldEntity;
import com.itransition.courseproject.entity.TopicEntity;
import com.itransition.courseproject.enums.FieldTypeEnum;
import com.itransition.courseproject.repository.AttachmentRepository;
import com.itransition.courseproject.repository.CollectionRepository;
import com.itransition.courseproject.repository.FieldRepository;
import com.itransition.courseproject.repository.TopicRepository;
import com.itransition.courseproject.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.UUDecoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;
    private final TopicRepository topicRepository;
    private final AttachmentRepository attachmentRepository;
    private final ModelMapper modelMapper;
    private final FieldRepository fieldRepository;
    private final String directory = "src/main/resources/static/";

    @Override
    public Boolean checkCollectionTopicName(String name) {
        return topicRepository.existsByName(name);
    }

    @Override
    public TopicEntity addTopic(TopicDto topicDto) {
        return topicRepository.save(new TopicEntity(topicDto.getName()));
    }

    @Override
    public List<TopicEntity> getTopicAll() {
        return topicRepository.findAll();
    }

    @Override
    public TopicEntity getTopic(UUID id) {
        return topicRepository.findById(id).get();
    }

    @Override
    public List<TopicEntity> getTopic(String name) {
        return topicRepository.findByName(name);
    }

    @Override
    public TopicEntity editTopic(UUID id, TopicDto topicDto) {
        TopicEntity topicEntity = topicRepository.findById(id).get();
        topicEntity.setName(topicDto.getName());
        return topicRepository.save(topicEntity);
    }

    @Override
    public void deleteTopic(UUID id) {
        topicRepository.deleteById(id);
    }

    @Override
    public AttachmentEntity addAttachment(MultipartFile file) {
            if (file != null){
                String originalName = file.getOriginalFilename();

                String[] split = originalName.split("\\.");
                String fileExtension = split[split.length - 1];

                if (!fileExtension.equals("jpg")){
                    throw new RuntimeException("Sorry file extension isn't available. File extension must be .jpg or .JPG");
                }

                String name = UUID.randomUUID().toString() + "." + fileExtension;

                AttachmentEntity attachment = new AttachmentEntity();
                attachment.setOriginalName(originalName);
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setName(name);

                attachmentRepository.save(attachment);

                Path path = Paths.get(directory + "/" + name);
                try {
                    Files.copy(file.getInputStream(),path);
                } catch (IOException e) {
                    throw new RuntimeException("File isn't upload");
                }

                return attachment;

            }
            return null;
    }

    @Override
    public AttachmentEntity getAttachment(UUID id) {
        return attachmentRepository.findById(id).get();
    }

    @Override
    public CollectionEntity addCollection(CollectionDto collectionDto) {
        return collectionRepository.save(new CollectionEntity(
                collectionDto.getName(),
                collectionDto.getDescription(),
                topicRepository.findById(UUID.fromString(collectionDto.getTopicId())).get(),
                attachmentRepository.findById(UUID.fromString(collectionDto.getAttachmentId())).get()
        ));
    }

    @Override
    public CollectionEntity editCollection(CollectionDto collectionDto) {
        return null;
    }

    @Override
    public CollectionEntity deleteCollection(UUID id) {
        return null;
    }

    @Override
    public List<CollectionResponse> getCollectionAll() {
       return modelMapper.map(
               collectionRepository.findAll(),
               new TypeToken<List<CollectionResponse>>(){}.getType());
    }

    @Override
    public CollectionEntity getCollection(UUID id) {
        return collectionRepository.findById(id).get();
    }

    @Override
    public List<CollectionResponse> getCollectionByUserId(String email) {
        return modelMapper.map(
                collectionRepository.findByCreatedById(email),
                new TypeToken<List<CollectionResponse>>(){}.getType());
    }

    @Override
    public List<CollectionResponse> getCollection(String name) {
        return null;
    }

    @Override
    public FieldEntity addField(FieldDto field) {
        FieldEntity fieldEntity = new FieldEntity();
        switch (field.getFieldType()) {
            case "STRING":
                fieldEntity.setFieldType(FieldTypeEnum.STRING);
                break;
            case "INTEGER":
                fieldEntity.setFieldType(FieldTypeEnum.INTEGER);
                break;
            case "BOOLEAN":
                fieldEntity.setFieldType(FieldTypeEnum.BOOLEAN);
                break;
            case "DATE":
                fieldEntity.setFieldType(FieldTypeEnum.DATE);
                break;
        }
        fieldEntity.setName(field.getName());
        fieldEntity = fieldRepository.save(fieldEntity);
        CollectionEntity collection = getCollection(UUID.fromString(field.getCollectionId()));
        Set<FieldEntity> fieldList = new HashSet<>();
        if (collection.getField().size() != 0){
            fieldList = collection.getField();
        }
        fieldList.add(fieldEntity);
        collection.setField(fieldList);
        collectionRepository.save(collection);
        return fieldEntity;
    }

    @Override
    public FieldEntity deleteField(UUID id) {
        return null;
    }

    @Override
    public FieldEntity editField(FieldDto field, UUID id) {
        return null;
    }

    @Override
    public FieldEntity getFieldById(UUID id) {
        return null;
    }

    @Override
    public List<FieldEntity> getFieldAll() {
        return null;
    }
}
