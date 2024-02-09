package com.mjc.school.service.dto;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthorDtoRequest {
    @Valid

    @Min(1)
    @Max(Long.MAX_VALUE)
    private Long id;
    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    public AuthorDtoRequest(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}