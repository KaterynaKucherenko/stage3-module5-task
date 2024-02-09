package com.mjc.school.service.mapper;


import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.CommentRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.interfaces.AuthorRepositoryInterface;
import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.repository.interfaces.TagRepositoryInterface;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring", uses = {TagMapper.class, CommentMapper.class, AuthorMapper.class})
public abstract class NewsMapper {
    @Autowired
    protected AuthorRepositoryInterface authorRepositoryInterface;
    @Autowired
    protected TagRepositoryInterface tagRepositoryInterface;
    @Autowired
    protected CommentRepositoryInterface commentRepositoryInterface;

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "authorModel", expression = "java(authorRepositoryInterface.readById(newsDtoRequest.authorId()).get())")
    @Mapping(target = "tags", expression = "java(newsDtoRequest.tags().stream().map(tagId -> tagRepositoryInterface.readById(tagId).get()).toList())")
    @Mapping(target = "comments", expression = "java(newsDtoRequest.comments().stream().map(commentId -> commentRepositoryInterface.readById(commentId).get()).toList())")
    public abstract  NewsModel DTONewsToModel(NewsDtoRequest newsDtoRequest);

@Mapping(source = "tags", target = "tagList")
@Mapping(source = "comments", target ="commentList" )
    public abstract NewsDtoResponse ModelNewsToDTO(NewsModel newsModel);

    public abstract List<NewsDtoResponse> ModelListToDtoList (List<NewsModel> newsModelList);






}
