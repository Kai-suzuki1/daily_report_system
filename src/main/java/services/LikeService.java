package services;


import actions.views.EmployeeView;
import actions.views.LikeConverter;
import actions.views.LikeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Report;


/**
 * いいね！テーブルの操作に関わる処理を行うクラス
 */
public class LikeService extends ServiceBase {

    /**
     * Likeテーブル内で該当の日報idからいいね！数を算出
     * @return　Long型　日報id情報ベースの合計数
     */
    public long countLike() {
        long likes_counts = (long)em.createNamedQuery(JpaConst.Q_LIK_COUNT, Long.class)
                .getSingleResult();
        return likes_counts;
    }

    /**
     * 該当の日報idと従業員idからいいね！が既にされていないか確認
     * @return Integer型 0＝未いいね！ 1=いいね！済
     */
    public Integer checkLike() {
        Integer likes_check = em.createNamedQuery(JpaConst.Q_LIK_CHECK, Integer.class)
                .getSingleResult();
        return likes_check;
    }


    /**
     * 日報詳細画面から押されたいいね！ボタンからデータを1件作成し、いいね！テーブルに登録する
     * @param rv 日報の登録内容
     * @return
     */
    public void create(EmployeeView ev, ReportView rv) {
        LikeView lvFinish = new LikeView();
        lvFinish.setEmployeeId(ev.getId());
        lvFinish.setReportId(rv.getId());
        createInternal(lvFinish);

    }

    /**
     * いいね！データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(LikeView lv) {

        em.getTransaction().begin();
        em.persist(LikeConverter.toModel(lv));
        em.getTransaction().commit();
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ReportView findOne(int id) {
        return ReportConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Report findOneInternal(int id) {
        return em.find(Report.class, id);
    }

}