package com.itransition.courseproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagedRequestDto {
    private static final int DEFAULT_SIZE = 50;

    private int page;
    private int size;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return (size != 0) ? size : DEFAULT_SIZE;
    }
}
