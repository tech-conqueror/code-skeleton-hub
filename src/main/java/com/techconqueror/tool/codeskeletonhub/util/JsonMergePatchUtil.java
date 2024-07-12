package com.techconqueror.tool.codeskeletonhub.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class JsonMergePatchUtil {

    private static ObjectMapper objectMapper;

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonMergePatchUtil.objectMapper = objectMapper;
    }

    public static <T> T patchEntity(T entity, Class<T> clz, List<JsonPatchOperation> patchOperations) {
        JsonNode userJsonNode = objectMapper.convertValue(entity, JsonNode.class);

        try {
            JsonPatch patchRef = new JsonPatch(patchOperations);
            JsonNode patchedNode = patchRef.apply(userJsonNode);
            return objectMapper.treeToValue(patchedNode, clz);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
