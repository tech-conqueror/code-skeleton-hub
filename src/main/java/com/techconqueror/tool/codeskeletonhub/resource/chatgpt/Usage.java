package com.techconqueror.tool.codeskeletonhub.resource.chatgpt;

public record Usage(
        Integer prompt_tokens,
        Integer completion_tokens,
        Integer total_tokens
) {}
