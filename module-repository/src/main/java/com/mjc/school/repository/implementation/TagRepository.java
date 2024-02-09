package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;


import com.mjc.school.repository.interfaces.TagRepositoryInterface;
import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements TagRepositoryInterface{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagRepository() {
    }


    @Override
    public List<TagModel> readAll(int page, int size, String sortBy) {
        List<String> sort = List.of(sortBy.split(","));
        String sorting = "SELECT a from TagModel a ORDER BY " + sort.get(0) + " " +sort.get(1);
        List<TagModel> result = entityManager.createQuery(sorting, TagModel.class).setFirstResult((page-1)*size).setMaxResults(page*size).getResultList();
        return result;
    }


    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }


    @Override
    public TagModel create(TagModel tagModel) {
        entityManager.persist(tagModel);
        return tagModel;
    }


    @Override
    public TagModel update(TagModel tagModel) {
        entityManager.merge(tagModel);
        return tagModel;
    }


    @Override
    public boolean deleteById(Long id) {
        TagModel tagModel = entityManager.getReference(TagModel.class, id);
        try {
            entityManager.remove(tagModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean existById(Long id) {
        TagModel tagModel = entityManager.getReference(TagModel.class, id);
        return tagModel != null;
    }


    @Override
    public List<TagModel> readListOfTagsByNewsId(Long newsId) {
        List<TagModel> result = entityManager.createQuery("SELECT a FROM TagModel a INNER JOIN a.news b WHERE b.id=:newsId", TagModel.class).setParameter("newsId", newsId).getResultList();
        return result;
    }
}

