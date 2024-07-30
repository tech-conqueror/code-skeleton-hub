package com.techconqueror.tool.codeskeletonhub.resource.chatgpt;

import java.util.List;

public record ChatCompletionRequest(
        String model,
        List<Message> messages
) {}
