package com.mjc.school.controller.impl;


import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import com.mjc.school.service.interfaces.NewsServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/news", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsServiceInterface newsService;


    @Autowired
    public NewsController(NewsServiceInterface newsService) {
        this.newsService = newsService;
    }

    @CommandHandler("1")
    @RequestMapping(method = RequestMethod.GET)
    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy) {
        return newsService.readAll(page, size, sortBy);
    }

    @CommandHandler("2")
    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse readById(@CommandParam("newsId") @PathVariable Long id) {
        return newsService.readById(id);
    }

    @CommandHandler("3")
    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDtoResponse create(@CommandBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @CommandHandler("4")
    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse update(@CommandBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @CommandHandler("5")
    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@CommandParam("newsId") @PathVariable Long id) {
        return newsService.deleteById(id);
    }

//    @CommandHandler("6")
//    @Override
//    public List<NewsDtoResponse> getNewsByParams(String tagName, Long tagId, String authorName, String title, String content) {
//        List<NewsDtoResponse> news = newsService.getNewsByParams(tagName, tagId, authorName, title, content);
//        return news;
//    }
}