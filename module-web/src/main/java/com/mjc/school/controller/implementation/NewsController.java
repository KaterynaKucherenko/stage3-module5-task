package com.mjc.school.controller.implementation;


import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.interfaces.AuthorServiceInterface;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import com.mjc.school.service.interfaces.NewsServiceInterface;
import com.mjc.school.service.interfaces.TagServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/news")
@Api(produces = "application/json")
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsServiceInterface newsService;
    private final AuthorServiceInterface authorService;
    private final TagServiceInterface tagService;
    private final CommentServiceInterface commentService;

    public NewsController(NewsServiceInterface newsService, AuthorServiceInterface authorService, TagServiceInterface tagService, CommentServiceInterface commentService) {
        this.newsService = newsService;
        this.authorService = authorService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @GetMapping
    @Override
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched all news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<NewsDtoResponse> readAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "created,dsc") String sortBy) {
        return newsService.readAll(page, size, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get news by ID", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched news by ID"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public NewsDtoResponse readById(@CommandParam("newsId") @PathVariable Long id) {
        return newsService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public NewsDtoResponse create(@RequestBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public NewsDtoResponse update(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(id,updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete news by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted news by ID"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void deleteById(@CommandParam("newsId") @PathVariable Long id) {
        newsService.deleteById(id);
    }


    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get news with provided parameters", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully fetched news with provided parameters"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<NewsDtoResponse> readListOfNewsByParams(
            @RequestParam(name = "tag_name", required = false) Optional<List<String>> tagName,
            @RequestParam(name = "tag_id", required = false) Optional<List<Long>> tagId,
            @RequestParam(name = "author_name", required = false) Optional<String> authorName,
            @RequestParam(name = "title", required = false) Optional<String> title,
            @RequestParam(name = "content", required = false) Optional<String> content) {
        return newsService.readListOfNewsByParams(tagName, tagId, authorName, title, content);
    }

    @GetMapping(value = "/{newsId:\\d+}/tag")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get tags of provided news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully fetched tags of provided news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<TagDtoResponse> readListOfTagsByNewsId(@PathVariable  Long newsId) {
        return tagService.readListOfTagsByNewsId(newsId);
    }

    @GetMapping(value = "/{newsId:\\d+}/author")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get author of provided news", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully fetched author of provided news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public AuthorDtoResponse readAuthorByNewsId(@PathVariable Long newsId) {
        return authorService.readAuthorByNewsId(newsId);
    }
    @GetMapping(value = "/{newsId:\\d+}/comment")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get comments of provided news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully fetched comments of provided news"),
            @ApiResponse(code = 400, message = "Invalid request from the client"),
            @ApiResponse(code = 404, message = "Resource is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<CommentDtoResponse> readListOfCommentsByNewsId(@PathVariable Long newsId) {
        return commentService.readListOfCommentsByNewsId(newsId);
    }

}
