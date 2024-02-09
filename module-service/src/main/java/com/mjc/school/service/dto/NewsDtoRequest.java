package com.mjc.school.service.dto;


import jakarta.validation.constraints.Size;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


public record NewsDtoRequest(
        @Min(1)
        @Max(Long.MAX_VALUE)
        Long id,
        @NotNull
        @Size(min = 5, max = 30)
        String title,
        @NotNull
        @Size(min = 5, max = 255)
        String content,
        Long authorId,
        List<Long> tags,
        List<Long> comments) {


}
