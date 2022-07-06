package com.itransition.courseproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String collectionId;
    private List<ItemDataDto> itemValues;
    private List<TagDto> tags;

}
