package com.techconqueror.tool.codeskeletonhub.domain.request;

import com.techconqueror.tool.codeskeletonhub.domain.search.SortDirection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortRequest {

    private String key;
    private SortDirection direction;
}
