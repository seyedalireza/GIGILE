package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Const;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Long score = 0L;
    private Long money = Const.START_MONEY;
    private City city = new City();
    private boolean isMyTurn = false;

    public void claculateScore() {

    }


}
