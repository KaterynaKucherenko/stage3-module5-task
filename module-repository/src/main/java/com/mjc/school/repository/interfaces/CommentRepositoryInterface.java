package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.CommentModel;

import java.util.List;

public interface CommentRepositoryInterface extends BaseRepository<CommentModel, Long> {
    List<CommentModel> readListOfCommentsByNewsId (Long newsId);
}
