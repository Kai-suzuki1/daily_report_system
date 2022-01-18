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
import constants.JpaConst;
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

    public void index() throws ServletException, IOException {

        int page = getPage();
        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        List<CommentView> cvs = service.getMyCommentsPerPage(page, ev);

        long allMyComments = service.countAllMyComments(ev);

        putRequestScope(AttributeConst.CMT_COUNTALL, allMyComments);
        putRequestScope(AttributeConst.MYCOMMENTS, cvs);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

//        String flush = getSessionScope(AttributeConst.FLUSH);
//        if (flush != null) {
//            putRequestScope(AttributeConst.FLUSH, flush);
//            removeSessionScope(AttributeConst.FLUSH);
//        }

        forward(ForwardConst.FW_CMT_INDEX);
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());

        ReportView rv = rpService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        putRequestScope(AttributeConst.REPORT, rv);

        List<CommentView> commentsOnReport = service.getAllCommentsOnReport(rv);
        putRequestScope(AttributeConst.COMMENTS_ON_REP, commentsOnReport);

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
    public void edit() throws ServletException, IOException {

        CommentView cmv = service.findOne(toNumber(getRequestParam(AttributeConst.CMT_ID)));

        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        if (cmv == null || cmv.getEmployee().getId() != ev.getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.COMMENT, cmv);

            forward(ForwardConst.FW_CMT_EDIT);
        }
    }

    public void update() throws ServletException, IOException {

        if (checkToken()) {

        CommentView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CMT_ID)));

        cv.setContent(getRequestParam(AttributeConst.CMT_CONTENT));

        List<String> errors = service.update(cv);

        if (errors.size() > 0) {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.COMMENT, cv);
            putRequestScope(AttributeConst.ERR, errors);

            forward(ForwardConst.FW_CMT_EDIT);
        } else {

            putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }
    }
  }
    public void destroy() throws ServletException, IOException{

            service.destory(toNumber(getRequestParam(AttributeConst.CMT_ID)));

            redirect(ForwardConst.ACT_CMT, ForwardConst.CMD_INDEX);

    }
}
