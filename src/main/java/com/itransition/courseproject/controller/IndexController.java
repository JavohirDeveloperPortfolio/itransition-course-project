package com.itransition.courseproject.controller;

import com.itransition.courseproject.service.impl.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elasticSearch")
@RequiredArgsConstructor
public class IndexController {
    private final IndexService service;

    @PostMapping("/recreate")
    public void recreateAllIndices() {
        service.recreateIndices(true);
    }
}
