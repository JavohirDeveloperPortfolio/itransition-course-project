package com.itransition.courseproject.controller;

import com.itransition.courseproject.dto.request.CollectionDto;
import com.itransition.courseproject.dto.request.FieldDto;
import com.itransition.courseproject.dto.request.TopicDto;
import com.itransition.courseproject.dto.response.ErrorResponse;
import com.itransition.courseproject.service.impl.CollectionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {
    private final CollectionServiceImpl collectionServiceImpl;

    @PostMapping("/topic/add")
    public ResponseEntity<?> addTopic(@RequestBody TopicDto topic){
        if (collectionServiceImpl.checkCollectionTopicName(topic.getName())){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"Error","Topic already exist"));
        }
        return ResponseEntity.ok(collectionServiceImpl.addTopic(topic));
    }

    @GetMapping("/topic/get")
    public ResponseEntity<?> getTopicAll(){
        return ResponseEntity.ok(collectionServiceImpl.getTopicAll());
    }

    @GetMapping("/topic/getById/{id}")
    public ResponseEntity<?> getTopicAll(@PathVariable(name = "id") UUID id){
        return ResponseEntity.ok(collectionServiceImpl.getTopic(id));
    }

    @GetMapping("/topic/getByName/{name}")
    public ResponseEntity<?> getTopicAll(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(collectionServiceImpl.getTopic(name));
    }

    @DeleteMapping("/topic/delete/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable(name = "id") UUID id){
        if (collectionServiceImpl.getTopic(id) == null){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"Error","Topic doesn't exist"));
        }
        collectionServiceImpl.deleteTopic(id);
        return ResponseEntity.ok("Topic deleted");
    }

    @PostMapping("/topic/edit/{id}")
    public ResponseEntity<?> editTopic(
            @PathVariable(name = "id") UUID id,
            @RequestBody TopicDto topicDto){
        if (collectionServiceImpl.getTopic(id) == null){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"Error","Topic doesn't exist"));
        }
        return ResponseEntity.ok(collectionServiceImpl.editTopic(id,topicDto));
    }

    @PostMapping("/attachment/upload")
    public ResponseEntity<UUID> upload(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(collectionServiceImpl.addAttachment(file).getId());
    }

    @GetMapping("/attachment/download/{id}")
    public ResponseEntity<?> getFile(
            @PathVariable UUID id
    ){
        return ResponseEntity.ok(collectionServiceImpl.getAttachment(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCollection(@RequestBody CollectionDto collectionDto){
        return ResponseEntity.ok(collectionServiceImpl.addCollection(collectionDto));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCollection(){
        return ResponseEntity.ok(collectionServiceImpl.getCollectionAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCollectionById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.ok(collectionServiceImpl.getCollection(id));
    }

    @GetMapping("/getByUsername/{email}")
    public ResponseEntity<?> getCollectionByUserId(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(collectionServiceImpl.getCollectionByUserId(email));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getCollectionByName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(collectionServiceImpl.getCollection(name));
    }

    @PostMapping("/field/add")
    public ResponseEntity<?> addCollectionField(@RequestBody FieldDto fieldDto){
        return ResponseEntity.ok(collectionServiceImpl.addField(fieldDto));
    }

}