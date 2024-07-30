package com.techconqueror.tool.codeskeletonhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long lockVersion = 0L;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleteFlag = false;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createAt;

    @CreatedBy
    private Long createBy;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastUpdateAt;

    @CreatedBy
    private Long lastUpdateBy;
}
