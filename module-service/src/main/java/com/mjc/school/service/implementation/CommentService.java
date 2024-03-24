package com.mjc.school.service.implementation;


import com.mjc.school.repository.implementation.CommentRepository;

import com.mjc.school.repository.model.CommentModel;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.exceptions.ElementNotFoundException;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import com.mjc.school.service.mapper.CommentMapper;
import com.mjc.school.service.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ErrorCodes.*;

@Service("commentService")
@Transactional
public class CommentService implements CommentServiceInterface {
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDtoResponse> readAll(int page, int size, String sortBy) {
        return commentMapper.listModelToDtoList(commentRepository.readAll(page, size, sortBy));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDtoResponse readById(Long id) {
        Optional<CommentModel> opt = commentRepository.readById(id);
        return opt.map(commentMapper::ModelCommentToDto).orElseThrow(() -> new ElementNotFoundException(String.format(NO_TAG_WITH_PROVIDED_ID.getErrorMessage(), id)));

    }

    @Override
    @Transactional
    public CommentDtoResponse create(@Valid CommentDtoRequest createRequest) {
        CommentModel commentModel = commentMapper.DtoCommentToModel(createRequest);
        return commentMapper.ModelCommentToDto((commentRepository.create(commentModel)));
    }

    @Override
    @Transactional
    public CommentDtoResponse update(Long id,@Valid CommentDtoRequest updateRequest) {
        if (commentRepository.existById(id)) {
            CommentModel commentModel = commentMapper.DtoCommentToModel(updateRequest);
            commentModel.setId(id);
            return commentMapper.ModelCommentToDto(commentRepository.update(commentModel));
        } else {
            throw new ElementNotFoundException(String.format(NO_COMMENT_WITH_PROVIDED_ID.getErrorMessage(),id));
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (commentRepository.existById(id)) {
            return commentRepository.deleteById(id);
        } else {
            throw new ElementNotFoundException(String.format(NO_COMMENT_WITH_PROVIDED_ID.getErrorMessage(), id));
        }
    }

@Override
    public List<CommentDtoResponse> readListOfCommentsByNewsId(Long newsId) {
        if (newsId != null && newsId>=0) {
            return commentMapper.listModelToDtoList(commentRepository.readListOfCommentsByNewsId(newsId));
        } else {
            throw new ElementNotFoundException(String.format(NO_COMMENTS_FOR_NEWS_ID.getErrorMessage(), newsId));


        }
    }
}

