package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NewsRepository() {
    }


    @Override
    public List<NewsModel> readAll() {
        List<NewsModel> result = entityManager.createQuery("SELECT n from NewsModel n", NewsModel.class).getResultList();
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


}