package com.itransition.courseproject.controller;

import com.itransition.courseproject.dto.request.CommentDto;
import com.itransition.courseproject.dto.request.ItemDto;
import com.itransition.courseproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemWithData(
            @RequestBody ItemDto itemDto
    ){
        return ResponseEntity.ok(itemService.addItem(itemDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getItem(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @GetMapping("/{collectionId}/get")
    public ResponseEntity<?> getCollectionItems(
            @PathVariable(name = "collectionId") String id){
        return ResponseEntity.ok(itemService.getCollectionItems(id));
    }

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(@RequestBody CommentDto comment){
        return ResponseEntity.ok(itemService.addComment(comment));
    }

    @DeleteMapping("comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id){
        return ResponseEntity.ok(itemService.deleteComment(id));
    }
}
