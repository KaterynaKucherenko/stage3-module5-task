package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("newsRepository")
public class NewsRepository extends AbstractDBRepository<NewsModel, Long> {
    public List<NewsModel> readListOfNewsByParams(List<String> tagName, List<Long> tagId, String authorName, String title, String content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);
        if (tagName != null && !tagName.isEmpty()) {
            Join<NewsModel, TagModel> newsJoinTags = root.join("tags");
            Predicate tagN = criteriaBuilder.equal(newsJoinTags.get("name"), tagName);
            query.select(root).where(tagN);
        }
        if (tagId != null && !tagId.isEmpty()) {
            Join<NewsModel, TagModel> newsJoinTags = root.join("tags");
            Predicate tagI = criteriaBuilder.in(newsJoinTags.get("id")).value(tagId);
            query.select(root).where(tagI);
        }
        if (authorName != null) {
            Join<NewsModel, AuthorModel> newsJoinAuthor = root.join("authorModel");
            Predicate authorN = criteriaBuilder.equal(newsJoinAuthor.get("name"), authorName);
            query.select(root).where(authorN);
        }
        if (title != null) {
            Predicate titlePart = criteriaBuilder.like(root.get("title"), "%" + title + "%");
            query.select(root).where(titlePart);
        }
        if (content != null) {
            Predicate contentPart = criteriaBuilder.like(root.get("content"), "%" + content + "%");
            query.select(root).where(contentPart);
        }
        TypedQuery<NewsModel> result = entityManager.createQuery(query);
        return result.getResultList();
    }

    @Override
    void update(NewsModel prevState, NewsModel nextState) {
        if (nextState.getTitle() != null && !nextState.getTitle().isBlank()) {
            prevState.setTitle(nextState.getTitle());
        }
        if (nextState.getContent() != null && !nextState.getContent().isBlank()) {
            prevState.setContent(nextState.getContent());
        }
        AuthorModel authorModel = nextState.getAuthorModel();
        if (authorModel != null && !authorModel.getName().isBlank()) {
            prevState.setAuthorModel(nextState.getAuthorModel());
        }
        List<TagModel> tagModels = nextState.getTags();
        if (tagModels != null && !tagModels.isEmpty()) {
            prevState.setTags(nextState.getTags());
        }
    }
}