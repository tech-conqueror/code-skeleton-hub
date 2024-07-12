package com.techconqueror.tool.codeskeletonhub.transformer;

public interface Transformer<E, D> {

    D toResource(E entity);

    E toEntity(D dto);
}
