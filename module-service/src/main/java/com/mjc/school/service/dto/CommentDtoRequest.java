package com.mjc.school.service.dto;

import com.mjc.school.repository.model.CommentModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDtoRequest {
    @Min(1)
    @Max(Long.MAX_VALUE)
    private Long id;
    @NotNull
    @Size(min = 5, max = 255)
    private String content;

    public CommentDtoRequest() {
    }

    public CommentDtoRequest(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
