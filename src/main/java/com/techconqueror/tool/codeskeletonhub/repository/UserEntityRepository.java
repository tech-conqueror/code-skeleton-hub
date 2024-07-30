package com.techconqueror.tool.codeskeletonhub.repository;

import com.techconqueror.tool.codeskeletonhub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {}
