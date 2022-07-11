package com.itransition.courseproject.service;

import com.itransition.courseproject.dto.request.SearchRequestDto;
import com.itransition.courseproject.entity.ItemEntity;

import java.util.List;

public interface MainService {
    List<ItemEntity> search(SearchRequestDto dto);
}
