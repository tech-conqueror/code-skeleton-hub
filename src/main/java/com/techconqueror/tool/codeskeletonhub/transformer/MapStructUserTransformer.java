package com.techconqueror.tool.codeskeletonhub.transformer;

import com.techconqueror.tool.codeskeletonhub.entity.UserEntity;
import com.techconqueror.tool.codeskeletonhub.resource.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructUserTransformer extends UserTransformer {

    MapStructUserTransformer INSTANCE = Mappers.getMapper(MapStructUserTransformer.class);

    @Override
    User toResource(UserEntity userEntity);

    @Override
    UserEntity toEntity(User user);
}
