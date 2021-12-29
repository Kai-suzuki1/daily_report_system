package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.LikeService;

public class LikeAction extends ActionBase {

     private LikeService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new LikeService();

        invoke();
        service.close();
    }

    public void create() throws ServletException, IOException {

//       CSRF対策
        if (checkToken()) {

//            セッションスコープからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

            //idを条件に日報データを取得する
            ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

            service.create(ev, rv);

            forward(ForwardConst.FW_REP_SHOW);

        }

    }
}