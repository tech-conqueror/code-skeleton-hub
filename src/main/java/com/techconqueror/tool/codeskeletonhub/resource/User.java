package com.techconqueror.tool.codeskeletonhub.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record User(
        Long id,
        @NotBlank String name,
        @Email String email,
        Long lockVersion,
        Boolean deleteFlag,
        Instant createAt,
        Long createBy,
        Instant lastUpdateAt,
        Long lastUpdateBy
) {}
