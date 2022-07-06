package com.itransition.courseproject.service.impl;

import com.itransition.courseproject.dto.request.ItemDataDto;
import com.itransition.courseproject.dto.request.ItemDto;
import com.itransition.courseproject.dto.request.TagDto;
import com.itransition.courseproject.entity.*;
import com.itransition.courseproject.repository.*;
import com.itransition.courseproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final FieldRepository fieldRepository;
    private final DataRepository dataRepository;
    private final CollectionRepository collectionRepository;
    private final TagRepository tagRepository;

    @Override
    public ItemEntity addItem(final ItemDto itemDto) {
        final ItemEntity item = new ItemEntity();

        CollectionEntity collectionEntity =
                collectionRepository.findById(UUID.fromString(itemDto.getCollectionId())).get();
        item.setCollection(collectionEntity);
        item.setName(itemDto.getName());

        List<TagDto> tags = itemDto.getTags();
        Set<TagEntity> tagEntities = new HashSet<>();
        for (TagDto tag:tags) {
            if (tagRepository.findByName(tag.getName()) == null){
                tagEntities.add(
                        tagRepository.save(new TagEntity(tag.getName()))
                );
            }else {
                tagEntities.add(tagRepository.findByName(tag.getName()));
            }
        }
        item.setTags(tagEntities);

        final List<ItemDataDto> itemValues = itemDto.getItemValues();
        final Set<FieldEntity> field = collectionEntity.getField();
        final Set<DataEntity> itemData = new HashSet<>();

        for (FieldEntity fieldEntity : field) {
            for (ItemDataDto itemValue : itemValues) {
                if (fieldEntity.getName().equals(itemValue.getFieldName()) && fieldEntity.getFieldType().equals(itemValue.getFieldType())){
                    final DataEntity data = new DataEntity();
                    data.setValue(itemValue.getValue());
                    data.setFields(fieldEntity);
                    itemData.add(
                            dataRepository.save(data)
                    );
                }
            }
        }
        item.setItemsData(itemData);

        return itemRepository.save(item);
    }
}