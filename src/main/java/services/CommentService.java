package services;

import actions.views.CommentConverter;
import actions.views.CommentView;
import models.Comment;

public class CommentService extends ServiceBase {


    public CommentView findOne(int id) {
        return CommentConverter.toView(findOneInternal(id));
    }

    private Comment findOneInternal(int id) {
        return em.find(Comment.class, id);
    }

}
