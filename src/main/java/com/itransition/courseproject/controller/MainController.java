package com.itransition.courseproject.controller;

import com.itransition.courseproject.dto.request.SearchRequestDto;
import com.itransition.courseproject.service.impl.MainServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.elasticsearch.search.sort.SortOrder.DESC;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MainServiceImpl mainService;

    @GetMapping("/search/?q={searchTerm}")
    public ResponseEntity<?> searchData(@PathVariable(name = "searchTerm") String searchTerm){
        SearchRequestDto requestDto = new SearchRequestDto();
        requestDto.setFields(new ArrayList<String>(){{
            add("name");
        }});
        requestDto.setSearchTerm(searchTerm);
        requestDto.setOrder(DESC);
        requestDto.setSortBy("name");
        return ResponseEntity.ok(mainService.search(requestDto));
    }
}
