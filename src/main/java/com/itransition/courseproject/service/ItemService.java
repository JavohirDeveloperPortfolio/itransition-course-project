package com.itransition.courseproject.service;

import com.itransition.courseproject.dto.request.ItemDto;
import com.itransition.courseproject.entity.ItemEntity;

public interface ItemService {
    ItemEntity addItem(ItemDto itemDto);
}
