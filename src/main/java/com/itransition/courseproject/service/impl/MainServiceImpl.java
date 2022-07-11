package com.itransition.courseproject.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itransition.courseproject.dto.request.SearchRequestDto;
import com.itransition.courseproject.entity.ItemEntity;
import com.itransition.courseproject.service.MainService;
import com.itransition.courseproject.utils.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(MainServiceImpl.class);

    private final RestHighLevelClient client;

    @Override
    public List<ItemEntity> search(final SearchRequestDto dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                SearchUtil.INDEX,
                dto
        );

        return searchInternal(request);
    }

    private List<ItemEntity> searchInternal(final SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<ItemEntity> items = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                items.add(
                        MAPPER.readValue(hit.getSourceAsString(), ItemEntity.class)
                );
            }

            return items;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
