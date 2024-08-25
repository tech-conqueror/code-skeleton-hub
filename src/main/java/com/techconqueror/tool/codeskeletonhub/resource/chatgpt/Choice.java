package com.techconqueror.tool.codeskeletonhub.resource.chatgpt;

public record Choice(
        Integer index,
        Message message,
        Object logprobs,
        String finish_reason
) {}
