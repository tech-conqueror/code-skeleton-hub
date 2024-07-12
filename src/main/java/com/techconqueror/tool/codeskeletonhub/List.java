package com.techconqueror.tool.codeskeletonhub;

public interface List<E> {

    int size();

    int isEmpty();

    void add(E element);

    E get(int index);

    void remove(int index);
}
