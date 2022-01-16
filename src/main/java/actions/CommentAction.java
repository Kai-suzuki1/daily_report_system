package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
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

//        // 今日の日付だけ入ったコメントインスタンスを送信
//        CommentView cv = new CommentView();
//        cv.setCommentDate(LocalDate.now());
//        putRequestScope(AttributeConst.COMMENT, cv);

        forward(ForwardConst.FW_CMT_NEW);
    }

    public void create() throws ServletException, IOException {
       if (checkToken()) {

           ReportView rv = rpService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

           EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

           LocalDate day = LocalDate.now();

           CommentView cv = new CommentView(
                   null,
                   ev,
                   rv,
                   getRequestParam(AttributeConst.CMT_CONTENT),
                   day,
                   null,
                   null);

           List<String> errors = service.create(cv);

           if (errors.size() > 0) {

               putRequestScope(AttributeConst.TOKEN, getTokenId());
               putRequestScope(AttributeConst.COMMENT, cv);
               putRequestScope(AttributeConst.ERR, errors);

               forward(ForwardConst.FW_CMT_NEW);
        } else {
                
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_COMMENTED.getMessage());

                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }

    }
  }
}
