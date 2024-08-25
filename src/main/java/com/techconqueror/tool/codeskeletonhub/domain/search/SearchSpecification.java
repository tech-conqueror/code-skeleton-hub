package com.techconqueror.tool.codeskeletonhub.domain.search;

import com.techconqueror.tool.codeskeletonhub.domain.request.FilterRequest;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.domain.request.SortRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchSpecification<T> implements Specification<T> {

    private final SearchRequest request;

    public SearchSpecification(final SearchRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(final @NonNull Root<T> root, final @NonNull CriteriaQuery<?> query, final CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequest filter : this.request.getFilters()) {
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(final Integer page, final Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }
}
