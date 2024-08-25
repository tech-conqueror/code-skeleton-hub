package com.techconqueror.tool.codeskeletonhub;

public class ArrayList<E> implements List<E> {

    private Object[] elements;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int isEmpty() {
        return 0;
    }

    @Override
    public void add(E element) {
        Object[] newElements = new Object[elements.length + 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public void remove(int index) {

    }
}
