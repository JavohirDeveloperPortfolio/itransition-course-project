package com.itransition.courseproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto extends PagedRequestDto{
    private List<String> fields;
    private String searchTerm;
    private String sortBy;
    private SortOrder order;
}
