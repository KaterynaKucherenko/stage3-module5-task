package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepository implements CommentRepositoryInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public  CommentRepository (){}

    @Override
    public List<CommentModel> readAll(int page, int size, String sortBy) {
        List<String> sort = List.of(sortBy.split(","));
        String sorting = "SELECT a from AuthorModel a ORDER BY " + sort.get(0) + " " +sort.get(1);
        List<CommentModel> result = entityManager.createQuery(sorting, CommentModel.class).setFirstResult((page-1)*size).setMaxResults(page*size).getResultList();
        return result;
    }

    @Override
    public Optional<CommentModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(CommentModel.class, id));
    }

    @Override
    public CommentModel create(CommentModel commentModel) {
        entityManager.persist(commentModel);
        return commentModel;
    }

    @Override
    public CommentModel update(CommentModel commentModel) {
        entityManager.merge(commentModel);
        return commentModel;
    }

    @Override
    public boolean deleteById(Long id) {
        CommentModel commentModel = entityManager.getReference(CommentModel.class, id);
        try {
            entityManager.remove(commentModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
    }}

    @Override
    public boolean existById(Long id) {
            CommentModel exist = entityManager.getReference(CommentModel.class, id);
            return exist != null;
    }

    @Override
    public List<CommentModel> readListOfCommentsByNewsId(Long newsId) {
        List<CommentModel> result = entityManager.createQuery("SELECT a FROM CommentModel a INNER JOIN a.newsModel b WHERE b.id=:newsId", CommentModel.class).setParameter("newsId", newsId).getResultList();
        return result;
    }
}
