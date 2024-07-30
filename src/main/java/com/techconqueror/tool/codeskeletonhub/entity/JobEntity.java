package com.techconqueror.tool.codeskeletonhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @ElementCollection
    private List<String> responsibilities;

    public JobEntity(String jobId, String description, List<String> responsibilities) {
        this.jobId = jobId;
        this.description = description;
        this.responsibilities = responsibilities;
    }
}
