package com.techconqueror.tool.codeskeletonhub.domain.search;

import com.techconqueror.tool.codeskeletonhub.domain.request.SortRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

public enum SortDirection {

    ASC {
        public <T> Order build(final Root<T> root, final CriteriaBuilder cb, final SortRequest request) {
            return cb.asc(root.get(request.getKey()));
        }
    },
    DESC {
        public <T> Order build(final Root<T> root, final CriteriaBuilder cb, final SortRequest request) {
            return cb.desc(root.get(request.getKey()));
        }
    };

    public abstract <T> Order build(final Root<T> root, final CriteriaBuilder cb, final SortRequest request);

}
