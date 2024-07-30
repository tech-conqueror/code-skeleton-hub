package com.techconqueror.tool.codeskeletonhub.service;

import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionRequest;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@HttpExchange(url = "${app.chatgpt.api.base-url}/chat/completions", accept = APPLICATION_JSON_VALUE, contentType = APPLICATION_JSON_VALUE)
public interface ChatCompletionService {

    @PostExchange
    ChatCompletionResponse createCompletion(@RequestBody ChatCompletionRequest chatCompletionRequest);
}
