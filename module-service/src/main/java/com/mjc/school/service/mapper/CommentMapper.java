package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings(value = {@Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true),
            @Mapping(target = "newsModel", ignore = true),
            @Mapping(target = "id", ignore = true)})
    CommentModel DtoCommentToModel(CommentDtoRequest commentDtoRequest);
    CommentDtoResponse ModelCommentToDto(CommentModel commentModel);
    List<CommentDtoResponse> listModelToDtoList(List<CommentModel> command);

}
