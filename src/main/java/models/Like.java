package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LikeデータのDTOモデル
 */
@Table(name = JpaConst.TABLE_LIK)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_LIK_COUNT,
            query = JpaConst.Q_LIK_COUNT_DEF
            ),
    @NamedQuery(
            name = JpaConst.Q_LIK_CHECK,
            query = JpaConst.Q_LIK_CHECK_DEF
            )
})


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Like {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.LIK_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社員番号
     */
    @Column(name = JpaConst.LIK_COL_EMP, nullable = false)
    private Integer employeeId;

    /**
     * 日報id
     */
    @Column(name = JpaConst.LIK_COL_REP, nullable = false)
    private Integer reportId;
}
