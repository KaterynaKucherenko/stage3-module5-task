package com.mjc.school.service.dto;

public class TagDtoResponse {
    private Long id;
    private String name;

    public TagDtoResponse() {
    }

    public TagDtoResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Tags ID: " + id + ", tags name: " + name;
    }
}
