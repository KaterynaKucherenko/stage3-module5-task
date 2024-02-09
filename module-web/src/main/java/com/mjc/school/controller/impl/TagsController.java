package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.interfaces.TagServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("tagsController")
public class TagsController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {
    private TagServiceInterface tagsService;

    @Autowired
    public TagsController(TagServiceInterface tagsService) {
        this.tagsService = tagsService;
    }

    @CommandHandler("12")
    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                        @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy) {
        return tagsService.readAll(page, size, sortBy);

    }

    @CommandHandler("13")
    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse readById(@CommandParam("tagId") @PathVariable Long id) {
        return tagsService.readById(id);
    }

    @CommandHandler("14")
    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagsService.create(createRequest);
    }

    @CommandHandler("15")
    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return tagsService.update(updateRequest);
    }

    @CommandHandler("16")
    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@CommandParam("tagId") @PathVariable Long id) {
        return tagsService.deleteById(id);
    }
}
