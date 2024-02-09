package com.mjc.school.service.interfaces;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.List;
import java.util.Optional;

public interface NewsServiceInterface extends BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    List<NewsDtoResponse> readListOfNewsByParams (Optional<List<String>> tagName, Optional<List<Long>> tagId, Optional<String> authorName, Optional<String> title, Optional<String> content);
}
