package com.techconqueror.tool.codeskeletonhub.controller;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.resource.UserResource;
import com.techconqueror.tool.codeskeletonhub.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResource>> getUsers(final @Valid @RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(userService.getUsers(searchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(final @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(final @Valid @RequestBody UserResource userResource) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userResource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replaceUser(final @PathVariable Long id, final @Valid @RequestBody UserResource userResource) {
        if (!id.equals(userResource.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        userService.replaceUser(userResource);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchUser(final @PathVariable Long id, final @RequestBody List<JsonPatchOperation> patchOperations) {
        userService.patchUser(id, patchOperations);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
