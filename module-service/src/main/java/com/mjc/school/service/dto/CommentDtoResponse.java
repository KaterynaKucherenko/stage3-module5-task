package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoResponse {
    private Long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String title) {
        this.content = title;
    }


    public LocalDateTime getCreateDate() {
        return created;
    }

    public void setCreateDate(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdateDate() {
        return modified;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.modified = modified;
    }
    public String toString() {
        return "Comment's ID: " + id + ", content: " + content + ", created: " + created + ", modified: " + modified;
    }
}
