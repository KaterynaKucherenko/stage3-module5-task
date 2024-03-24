package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.ElementNotFoundException;
import com.mjc.school.service.interfaces.TagServiceInterface;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ErrorCodes.NO_TAGS_FOR_NEWS_ID;
import static com.mjc.school.service.exceptions.ErrorCodes.NO_TAG_WITH_PROVIDED_ID;

@Service("tagsService")
@Transactional
public class TagsService implements TagServiceInterface {
    private TagRepository tagsRepository;
    private TagMapper tagMapper;

    @Autowired
    public TagsService(TagRepository  tagsRepository, TagMapper tagMapper) {
        this.tagsRepository = tagsRepository;
        this.tagMapper=tagMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDtoResponse> readAll(int page, int size, String sortBy) {
        return tagMapper.listModelToDtoList(tagsRepository.readAll(page, size, sortBy));
    }

    @Override
    @Transactional(readOnly = true)
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> opt = tagsRepository.readById(id);
        return opt.map(tagMapper::ModelTagsToDto).orElseThrow(()-> new ElementNotFoundException(String.format(NO_TAG_WITH_PROVIDED_ID.getErrorMessage(), id)));

    }

    @Override
    @Transactional
    public TagDtoResponse create(@Valid TagDtoRequest createRequest) {
        TagModel tagModel = tagMapper.DtoTagsToModel(createRequest);
        return  tagMapper.ModelTagsToDto(tagsRepository.create(tagModel));
    }

    @Override
    @Transactional
    public TagDtoResponse update(Long id,@Valid TagDtoRequest updateRequest) {
        if(tagsRepository.existById(id)){
            TagModel tagModel = tagMapper.DtoTagsToModel(updateRequest);
            tagModel.setId(id);
            return tagMapper.ModelTagsToDto(tagsRepository.update(tagModel));
        }
        else { throw  new ElementNotFoundException(String.format(NO_TAG_WITH_PROVIDED_ID.getErrorMessage(),id));
    }

        }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if(tagsRepository.existById(id)){
           return tagsRepository.deleteById(id);
        }
        else { throw  new ElementNotFoundException(String.format(NO_TAG_WITH_PROVIDED_ID.getErrorMessage(), id));
    }


    }

    public List<TagDtoResponse> readListOfTagsByNewsId(Long newsId) {
        if (newsId!=null && newsId>=0){
        return tagMapper.listModelToDtoList(tagsRepository.readListOfTagsByNewsId(newsId));
    }
    else {throw  new ElementNotFoundException(String.format(NO_TAGS_FOR_NEWS_ID.getErrorMessage(), newsId));

    }
    }}

