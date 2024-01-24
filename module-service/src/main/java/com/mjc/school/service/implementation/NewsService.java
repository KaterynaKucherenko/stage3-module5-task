package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.mapper.Mapper;
//import com.mjc.school.service.validation.ValidateNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("newsService")
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream().map(Mapper.INSTANCE::ModelNewsToDTO).toList();
    }


    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> opt = newsRepository.readById(id);
        return opt.map(Mapper.INSTANCE::ModelNewsToDTO).orElse(null);
    }


    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return Mapper.INSTANCE.ModelNewsToDTO(newsRepository.create(Mapper.INSTANCE.DTONewsToModel(createRequest)));
    }


    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return Mapper.INSTANCE.ModelNewsToDTO(newsRepository.update(Mapper.INSTANCE.DTONewsToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }


//    public List<NewsDtoResponse> getNewsByParams(String tagName, Long tagId, String authorName, String title, String content) {
//        return newsRepository.getNewsByParams(tagName, tagId, authorName, title, content).stream().map(Mapper.INSTANCE::ModelNewsToDTO).collect(Collectors.toList());
//
//
//    }
}