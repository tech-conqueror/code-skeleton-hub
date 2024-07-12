package com.techconqueror.tool.codeskeletonhub.domain.request;

import com.techconqueror.tool.codeskeletonhub.domain.search.FieldType;
import com.techconqueror.tool.codeskeletonhub.domain.search.Operator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {

    private String key;
    private FieldType fieldType;
    private Operator operator;
    private Object value;
}
