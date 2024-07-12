package com.techconqueror.tool.codeskeletonhub.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class SearchRequest {

    private List<FilterRequest> filters;
    private List<SortRequest> sorts;
    private Integer page;
    private Integer size;

    public List<FilterRequest> getFilters() {
        return Optional.ofNullable(this.filters).orElse(new ArrayList<>());
    }

    public List<SortRequest> getSorts() {
        return Optional.ofNullable(this.sorts).orElse(new ArrayList<>());
    }
}
