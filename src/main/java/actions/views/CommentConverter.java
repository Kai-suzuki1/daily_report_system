package actions.views;



import java.util.ArrayList;
import java.util.List;

import models.Comment;

public class CommentConverter {

    public static Comment toModel(CommentView cv) {
        return new Comment(
                cv.getId(),
                EmployeeConverter.toModel(cv.getEmployee()),
                ReportConverter.toModel(cv.getReport()),
                cv.getContent(),
                cv.getCommentDate(),
                cv.getCreatedAt(),
                cv.getUpdatedAt());
    }

    public static CommentView toView(Comment c) {
        if (c == null) {
            return null;
        }

        return new CommentView(
                c.getId(),
                EmployeeConverter.toView(c.getEmployee()),
                ReportConverter.toView(c.getReport()),
                c.getContent(),
                c.getCommentDate(),
                c.getCreatedAt(),
                c.getUpdatedAt());
    }

    public static List<CommentView> toViewList(List<Comment> list){
        List<CommentView> evs = new ArrayList<>();

        for(Comment c : list) {
            evs.add(toView(c));
        }
        return evs;
    }

    public static void copyViewtoModel(Comment c, CommentView cv) {

                c.setId(cv.getId());
                c.setEmployee(EmployeeConverter.toModel(cv.getEmployee()));
                c.setReport(ReportConverter.toModel(cv.getReport()));
                c.setContent(cv.getContent());
                c.setCommentDate(cv.getCommentDate());
                c.setCreatedAt(cv.getCreatedAt());
                c.setUpdatedAt(cv.getUpdatedAt());
    }

}
