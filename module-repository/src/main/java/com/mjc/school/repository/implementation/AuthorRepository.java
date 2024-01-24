package com.mjc.school.repository.implementation;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository("authorRepository")
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorRepository() {
    }


    @Override
    public List<AuthorModel> readAll() {
        List<AuthorModel> result = entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
        return result;
    }


    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }


    @Override
    public AuthorModel create(AuthorModel authorModel) {
        entityManager.persist(authorModel);
        return authorModel;
    }

    @Override
    public AuthorModel update(AuthorModel authorModel) {
        return entityManager.merge(authorModel);
    }


    @Override
    public boolean deleteById(Long id) {
        AuthorModel authorModel = entityManager.getReference(AuthorModel.class, id);
        try {
            entityManager.remove(authorModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean existById(Long id) {
        AuthorModel exist = entityManager.getReference(AuthorModel.class, id);
        return exist != null;
    }


}