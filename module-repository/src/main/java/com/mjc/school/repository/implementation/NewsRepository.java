package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements NewsRepositoryInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NewsRepository() {
    }


    @Override
    public List<NewsModel> readAll(int page, int size, String sortBy) {
        List<String> sort = List.of(sortBy.split(","));
        String sorting = "SELECT a from NewsModel a ORDER BY " + sort.get(0) + " " +sort.get(1);
        List<NewsModel> result = entityManager.createQuery(sorting, NewsModel.class).setFirstResult((page-1)*size).setMaxResults(page*size).getResultList();
        return result;
    }


    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }


    @Override
    public NewsModel create(NewsModel newsModel) {
        entityManager.persist(newsModel);
        return newsModel;
    }


    @Override
    public NewsModel update(NewsModel newsModel) {
        entityManager.merge(newsModel);
        return newsModel;
    }


    @Override
    public boolean deleteById(Long id) {
        NewsModel newsModel = entityManager.getReference(NewsModel.class, id);
        try {
            entityManager.remove(newsModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean existById(Long id) {
        NewsModel exist = entityManager.getReference(NewsModel.class, id);
        return exist != null;
    }


    @Override
    public List<NewsModel> readListOfNewsByParams(Optional<List<String>> tagName, Optional<List<Long>> tagId, Optional<String> authorName, Optional<String> title, Optional<String> content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);
        List<Predicate> predicates = new ArrayList<>();
        if (tagName.isPresent()|| tagId.isPresent()){
            Join<NewsModel, TagModel> newsJoinTags = root.join("tags");
            if (tagName.isPresent()){
            predicates.add(newsJoinTags.get("name").in(tagName));}
            if (tagId.isPresent()){
                predicates.add(newsJoinTags.get("id").in(tagId));
            }
        }
        if (authorName.isPresent()){
            Join<NewsModel, AuthorModel> newsJoinAuthor = root.join("author");
            predicates.add(criteriaBuilder.equal(newsJoinAuthor.get("name"), authorName));
        }
        if (title.isPresent()){
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
        if (content.isPresent()){
            predicates.add(criteriaBuilder.like(root.get("content"), "%" + content + "%"));
        }
        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}