package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeView {
    /**
     * id
     */
    private Integer id;

    /**
     * 社員番号
     */
    private EmployeeView employee;

    /**
     * 日報id
     */
    private Integer reportId;

}
