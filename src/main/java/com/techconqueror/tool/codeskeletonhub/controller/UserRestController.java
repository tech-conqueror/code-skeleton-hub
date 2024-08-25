package com.techconqueror.tool.codeskeletonhub.controller;

import com.github.fge.jsonpatch.JsonPatchOperation;
import com.techconqueror.tool.codeskeletonhub.domain.request.SearchRequest;
import com.techconqueror.tool.codeskeletonhub.resource.User;
import com.techconqueror.tool.codeskeletonhub.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(final @Valid @RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(userService.getUsers(searchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(final @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(final @Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replaceUser(final @PathVariable Long id, final @Valid @RequestBody User user) {
        if (!id.equals(user.id())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        userService.replaceUser(user);
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
