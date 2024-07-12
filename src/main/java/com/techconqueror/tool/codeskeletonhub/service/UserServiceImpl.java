package com.techconqueror.tool.codeskeletonhub.service;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.entity.User;
import com.techconqueror.tool.codeskeletonhub.exception.ResourceNotFoundException;
import com.techconqueror.tool.codeskeletonhub.repository.UserRepository;
import com.techconqueror.tool.codeskeletonhub.resource.UserResource;
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

    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    @Override
    public Page<UserResource> getUsers(final SearchRequest searchRequest) {
        SearchSpecification<User> specification = new SearchSpecification<>(searchRequest);
        Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
        return userRepository.findAll(specification, pageable).map(userTransformer::toResource);
    }

    @Override
    public UserResource getUserById(final Long id) {
        return userRepository.findById(id).map(userTransformer::toResource).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserResource createUser(final UserResource userResource) {
        User user = userRepository.save(userTransformer.toEntity(userResource));
        return userTransformer.toResource(user);
    }

    @Override
    public void replaceUser(final UserResource userResource) {
        User user = userTransformer.toEntity(userResource);

        if (!userRepository.existsById(user.getId()))
            throw new ResourceNotFoundException();

        userRepository.save(user);
    }

    @Override
    public void patchUser(Long id, List<JsonPatchOperation> patchOperations) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        userRepository.save(patchEntity(user, User.class, patchOperations));
    }

    @Override
    public void deleteUser(final Long id) {
        if (!userRepository.existsById(id))
            return;

        userRepository.deleteById(id);
    }
}
