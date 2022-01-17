package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CommentConverter;
import actions.views.CommentView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Comment;
import models.validators.CommentValidator;

public class CommentService extends ServiceBase {

    //　日報idに紐付いたコメントを全県取得
    public List<CommentView> getAllCommentsOnReport(ReportView rv) {
        List<Comment> comments = em.createNamedQuery(JpaConst.Q_CMT_GET_ALL_COMMENTS_ON_REP, Comment.class)
                                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                                .getResultList();
                       return CommentConverter.toViewList(comments);
    }


    // 新規コメント登録
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

    // 該当のコメントを一件取得
    public CommentView findOne(int id) {
        return CommentConverter.toView(findOneInternal(id));
    }

    private Comment findOneInternal(int id) {
        return em.find(Comment.class, id);
    }

}
