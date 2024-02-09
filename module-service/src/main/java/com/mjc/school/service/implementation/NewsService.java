package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ElementNotFoundException;
import com.mjc.school.service.interfaces.NewsServiceInterface;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ErrorCodes.NO_NEWS_WITH_PROVIDED_ID;

@Service("newsService")
public class NewsService implements NewsServiceInterface {
    private NewsRepository newsRepository;
    private NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public List<NewsDtoResponse> readAll(int page, int size, String sortBy) {
        return newsMapper.ModelListToDtoList((newsRepository.readAll(page, size, sortBy)));
    }


    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> opt = newsRepository.readById(id);
        return opt.map(newsMapper::ModelNewsToDTO).orElseThrow(()-> new ElementNotFoundException(String.format(NO_NEWS_WITH_PROVIDED_ID.getErrorMessage(), id)));

    }


    @Override
    public NewsDtoResponse create(@Valid NewsDtoRequest createRequest) {
        NewsModel newsModel = newsMapper.DTONewsToModel(createRequest);
        return newsMapper.ModelNewsToDTO(newsRepository.create(newsModel));
    }


    @Override
    public NewsDtoResponse update(@Valid NewsDtoRequest updateRequest) {
        if (newsRepository.existById(updateRequest.id())) {
            NewsModel newsModel = newsMapper.DTONewsToModel(updateRequest);
            return newsMapper.ModelNewsToDTO(newsRepository.update(newsModel));
        } else {
            throw new ElementNotFoundException(String.format(NO_NEWS_WITH_PROVIDED_ID.getErrorMessage(), updateRequest.id()));
        }

    }

    @Override
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)) {
            return newsRepository.deleteById(id);
        } else {
            throw new ElementNotFoundException(String.format(NO_NEWS_WITH_PROVIDED_ID.getErrorMessage(), id));
        }
    }

    @Override
    public List<NewsDtoResponse> readListOfNewsByParams(Optional<List<String>> tagName, Optional<List<Long>> tagId, Optional<String> authorName, Optional<String> title, Optional<String> content) {
        return newsMapper.ModelListToDtoList(newsRepository.readListOfNewsByParams(tagName, tagId, authorName, title, content));
    }
}



