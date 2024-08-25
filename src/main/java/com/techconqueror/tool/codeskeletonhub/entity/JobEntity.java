package com.techconqueror.tool.codeskeletonhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "job")
public class JobEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, unique = true)
    private String jobId;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private List<String> responsibilities;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus jobStatus;

    public JobEntity(String jobId, String description, List<String> responsibilities, JobStatus jobStatus) {
        this.jobId = jobId;
        this.description = description;
        this.responsibilities = responsibilities;
        this.jobStatus = jobStatus;
    }
}
