package com.mjc.school.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record TagDtoRequest(
        @Min(1)
        @Max(Long.MAX_VALUE)
        Long id,
        @NotNull
        @Size(min = 3, max = 15)
        String name) {
}
