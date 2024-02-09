package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;
import java.util.Optional;

public interface NewsRepositoryInterface extends BaseRepository<NewsModel, Long> {
    List<NewsModel> readListOfNewsByParams (Optional<List<String>> tagName, Optional<List<Long>> tagId, Optional<String> authorName, Optional<String> title,Optional<String> content);
}
