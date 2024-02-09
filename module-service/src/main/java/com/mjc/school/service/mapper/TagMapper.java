package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;

import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagModel DtoTagsToModel(TagDtoRequest tagDtoRequest);

    List<TagDtoResponse> listModelToDtoList(List<TagModel> tags);

    TagDtoResponse ModelTagsToDto(TagModel tagModel);
}
