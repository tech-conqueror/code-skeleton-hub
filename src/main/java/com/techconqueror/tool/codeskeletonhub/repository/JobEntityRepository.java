package com.techconqueror.tool.codeskeletonhub.repository;

import com.techconqueror.tool.codeskeletonhub.entity.JobEntity;
import com.techconqueror.tool.codeskeletonhub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobEntityRepository extends JpaRepository<JobEntity, Long>, JpaSpecificationExecutor<UserEntity> {}
