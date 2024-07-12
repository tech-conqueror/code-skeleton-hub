package com.techconqueror.tool.codeskeletonhub.domain.search;

import com.techconqueror.tool.codeskeletonhub.domain.request.FilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public enum Operator {

    EQUAL {
        public <T> Predicate build(final Root<T> root, final CriteriaBuilder cb, final FilterRequest request, final Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = root.get(request.getKey());
            return cb.and(cb.equal(key, value), predicate);
        }
    };

    public abstract <T> Predicate build(final Root<T> root, final CriteriaBuilder cb, final FilterRequest request, final Predicate predicate);
}
