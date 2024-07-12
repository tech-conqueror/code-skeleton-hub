package com.techconqueror.tool.codeskeletonhub.service;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.resource.UserResource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserResource> getUsers(SearchRequest searchRequest);

    UserResource getUserById(Long id);

    UserResource createUser(UserResource userResource);

    void replaceUser(UserResource userResource);

    void patchUser(Long id, List<JsonPatchOperation> patchOperations);

    void deleteUser(Long id);
}
