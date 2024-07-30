package com.techconqueror.tool.codeskeletonhub.service;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.entity.UserEntity;
import com.techconqueror.tool.codeskeletonhub.exception.ResourceNotFoundException;
import com.techconqueror.tool.codeskeletonhub.repository.UserEntityRepository;
import com.techconqueror.tool.codeskeletonhub.resource.User;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.domain.search.SearchSpecification;
import com.techconqueror.tool.codeskeletonhub.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.techconqueror.tool.codeskeletonhub.util.JsonMergePatchUtil.patchEntity;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    private final UserTransformer userTransformer;

    @Override
    public Page<User> getUsers(final SearchRequest searchRequest) {
        SearchSpecification<UserEntity> specification = new SearchSpecification<>(searchRequest);
        Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
        return userEntityRepository.findAll(specification, pageable).map(userTransformer::toResource);
    }

    @Override
    public User getUserById(final Long id) {
        return userEntityRepository.findById(id).map(userTransformer::toResource).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User createUser(final User userResource) {
        UserEntity userEntity = userEntityRepository.save(userTransformer.toEntity(userResource));
        return userTransformer.toResource(userEntity);
    }

    @Override
    public void replaceUser(final User userResource) {
        UserEntity userEntity = userTransformer.toEntity(userResource);

        if (!userEntityRepository.existsById(userEntity.getId()))
            throw new ResourceNotFoundException();

        userEntityRepository.save(userEntity);
    }

    @Override
    public void patchUser(Long id, List<JsonPatchOperation> patchOperations) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        userEntityRepository.save(patchEntity(userEntity, UserEntity.class, patchOperations));
    }

    @Override
    public void deleteUser(final Long id) {
        if (!userEntityRepository.existsById(id))
            return;

        userEntityRepository.deleteById(id);
    }
}
