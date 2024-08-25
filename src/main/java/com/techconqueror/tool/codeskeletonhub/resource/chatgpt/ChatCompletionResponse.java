package com.techconqueror.tool.codeskeletonhub.resource.chatgpt;

import java.util.List;

public record ChatCompletionResponse(
    String id,
    String object,
    Long created,
    String model,
    String system_fingerprint,
    List<Choice> choices,
    Usage usage
) {}
