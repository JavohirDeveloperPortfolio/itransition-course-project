package com.itransition.courseproject.service;

import com.itransition.courseproject.dto.request.CommentDto;
import com.itransition.courseproject.dto.request.ItemDto;
import com.itransition.courseproject.entity.CommentEntity;
import com.itransition.courseproject.entity.ItemEntity;

import java.util.Optional;

public interface ItemService {
    ItemEntity addItem(ItemDto itemDto);
    ItemEntity getItem(String id);

    Optional<ItemEntity> getCollectionItems(String id);

    CommentEntity addComment(CommentDto comment);

    String deleteComment(String id);
}
