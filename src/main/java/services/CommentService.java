package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CommentConverter;
import actions.views.CommentView;
import models.Comment;
import models.validators.CommentValidator;

public class CommentService extends ServiceBase {

    public List<String> create(CommentView cv) {
        List<String> errors = CommentValidator.validate(cv);

        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            cv.setCreatedAt(ldt);
            cv.setUpdatedAt(ldt);
            createInternal(cv);
        }

        return errors;
    }

    private void createInternal(CommentView cv) {
        em.getTransaction().begin();
        em.persist(CommentConverter.toModel(cv));
        em.getTransaction().commit();
    }

    public CommentView findOne(int id) {
        return CommentConverter.toView(findOneInternal(id));
    }

    private Comment findOneInternal(int id) {
        return em.find(Comment.class, id);
    }

}
