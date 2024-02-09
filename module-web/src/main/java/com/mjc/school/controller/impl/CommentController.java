package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandParam;

import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import com.mjc.school.service.interfaces.NewsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class CommentController implements BaseController<CommentDtoRequest, CommentDtoResponse, Long> {
    private final CommentServiceInterface commentService;
    private final NewsServiceInterface newsService;

    @Autowired
    public CommentController(CommentServiceInterface commentService, NewsServiceInterface newsService) {
        this.commentService = commentService;
        this.newsService = newsService;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy) {
        return this.commentService.readAll(page, size, sortBy);

    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse readById(@CommandParam("commentId") @PathVariable Long id) {
        return commentService.readById(id);
    }


    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse update(CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@CommandParam("commentId") @PathVariable Long id) {
        return commentService.deleteById(id);
    }
}
