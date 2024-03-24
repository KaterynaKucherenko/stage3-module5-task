package com.mjc.school.service.dto;

import jakarta.validation.constraints.Size;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public record NewsDtoRequest(
        @NotNull
        @Size(min = 5, max = 30)
        String title,
        @NotNull
        @Size(min = 5, max = 255)
        String content,
        @NotNull
        Long authorId,
        List<Long> tags,
        List<Long> comments) {
    public NewsDtoRequest {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        if (comments == null) {
            comments = new ArrayList<>();
        }
    }


}
