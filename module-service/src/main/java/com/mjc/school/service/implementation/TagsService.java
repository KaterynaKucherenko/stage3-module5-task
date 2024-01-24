package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("tagsService")
public class TagsService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {
    private BaseRepository<TagModel, Long> tagsRepository;

    @Autowired
    public TagsService(BaseRepository<TagModel, Long> tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return tagsRepository.readAll().stream().map(Mapper.INSTANCE::ModelTagsToDto).toList();
    }

    @Override
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> opt = tagsRepository.readById(id);
        return opt.map(Mapper.INSTANCE::ModelTagsToDto).orElse(null);
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return Mapper.INSTANCE.ModelTagsToDto(tagsRepository.create(Mapper.INSTANCE.DtoTagsToModel(createRequest)));
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return Mapper.INSTANCE.ModelTagsToDto(tagsRepository.update(Mapper.INSTANCE.DtoTagsToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return tagsRepository.deleteById(id);

    }
}
