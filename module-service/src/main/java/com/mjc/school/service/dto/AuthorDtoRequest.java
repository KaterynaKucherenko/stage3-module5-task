package com.mjc.school.service.dto;

import lombok.Data;


import java.util.List;

@Data
public class AuthorDtoRequest {

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

}