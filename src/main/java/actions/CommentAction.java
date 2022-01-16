package actions;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.CommentService;
import services.ReportService;

public class CommentAction extends ActionBase {

    private CommentService service;
    private ReportService rpService;

    @Override
    public void process() throws ServletException, IOException {
        service = new CommentService();
        rpService = new ReportService();

        invoke();

        service.close();
        rpService.close();
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());

        ReportView rv = rpService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        putRequestScope(AttributeConst.REPORT, rv);

        // 今日の日付だけ入ったコメントインスタンスを送信
        CommentView cv = new CommentView();
        cv.setCommentDate(LocalDate.now());
        putRequestScope(AttributeConst.COMMENT, cv);

        forward(ForwardConst.FW_CMT_NEW);
    }
}
