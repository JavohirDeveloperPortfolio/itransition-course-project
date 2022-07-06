package com.itransition.courseproject.controller;

import com.itransition.courseproject.dto.request.ItemDto;
import com.itransition.courseproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
