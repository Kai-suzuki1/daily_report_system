package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_CMT)

@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CMT_GET_ALL_COMMENTS_ON_REP,
            query = JpaConst.Q_CMT_GET_ALL_COMMENTS_ON_REP_DEF)
})


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @Column(name =JpaConst.CMT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.CMT_COL_EMP, nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = JpaConst.CMT_COL_REP, nullable = false)
    private Report report;

    @Lob
    @Column(name = JpaConst.CMT_COL_CONTENT, nullable = false)
    private String content;

    @Column(name = JpaConst.CMT_COL_CMT_DATE, nullable = false)
    private LocalDate commentDate;

    @Column(name = JpaConst.CMT_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = JpaConst.CMT_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
