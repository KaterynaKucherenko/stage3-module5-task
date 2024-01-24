package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("tagsController")
public class TagsController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {
    private BaseService<TagDtoRequest, TagDtoResponse, Long> tagsService;

    @Autowired
    public TagsController(BaseService<TagDtoRequest, TagDtoResponse, Long> tagsService) {
        this.tagsService = tagsService;
    }

    @CommandHandler("12")
    @Override
    public List<TagDtoResponse> readAll() {
        return tagsService.readAll();

    }

    @CommandHandler("13")
    @Override
    public TagDtoResponse readById(Long id) {
        return tagsService.readById(id);
    }

    @CommandHandler("14")
    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagsService.create(createRequest);
    }

    @CommandHandler("15")
    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return tagsService.update(updateRequest);
    }

    @CommandHandler("16")
    @Override
    public boolean deleteById(Long id) {
        return tagsService.deleteById(id);
    }
}
