package com.techconqueror.tool.codeskeletonhub.service;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.resource.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<User> getUsers(SearchRequest searchRequest);

    User getUserById(Long id);

    User createUser(User user);

    void replaceUser(User user);

    void patchUser(Long id, List<JsonPatchOperation> patchOperations);

    void deleteUser(Long id);
}
