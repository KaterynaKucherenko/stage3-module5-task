package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;

import java.util.Optional;

public interface AuthorRepositoryInterface extends BaseRepository<AuthorModel, Long> {
    Optional<AuthorModel> readAuthorByNewsId (Long newsId);

}
