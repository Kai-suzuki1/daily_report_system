package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.LikeService;
import services.ReportService;

public class LikeAction extends ActionBase {

     private LikeService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new LikeService();

        invoke();
        service.close();
    }

    public void create() throws ServletException, IOException {

            // CSRF対策
//          if (checkToken()) {

              // セッションスコープからログイン中の従業員情報を取得
              EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

              //idを条件に日報データを取得する
              ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));


              if (ev.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()) {

                  // いいね！を登録
                  service.create(ev, rv);

                  ReportService reService = new ReportService();

                  // admin_readカラムに「１」をセット
                  rv.setAdminRead(AttributeConst.REP_READ.getIntegerValue());
                  reService.readReport(rv);


                  //セッションにいいね！登録完了のフラッシュメッセージを設定
                  putSessionScope(AttributeConst.FLUSH, MessageConst.I_LIKED.getMessage());
                  putSessionScope(AttributeConst.REPORT, rv);

                  redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

              } else {

                  // いいね！を登録
                  service.create(ev, rv);

                  //セッションにいいね！登録完了のフラッシュメッセージを設定
                  putSessionScope(AttributeConst.FLUSH, MessageConst.I_LIKED.getMessage());
                  putSessionScope(AttributeConst.REPORT, rv);

                  redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

              }
        }
//  }
}
