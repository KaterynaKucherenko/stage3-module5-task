package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;


import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagRepository() {
    }


    @Override
    public List<TagModel> readAll() {
        List<TagModel> result = entityManager.createQuery("SELECT a from TagModel a", TagModel.class).getResultList();
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


}

