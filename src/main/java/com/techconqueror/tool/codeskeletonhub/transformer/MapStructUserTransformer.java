package com.techconqueror.tool.codeskeletonhub.transformer;

import com.techconqueror.tool.codeskeletonhub.entity.User;
import com.techconqueror.tool.codeskeletonhub.resource.UserResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructUserTransformer extends UserTransformer {

    MapStructUserTransformer INSTANCE = Mappers.getMapper(MapStructUserTransformer.class);

    @Override
    UserResource toResource(User user);

    @Override
    User toEntity(UserResource userResource);
}
