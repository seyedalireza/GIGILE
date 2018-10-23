package model.gilgFun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;
import utils.Const;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Market extends Entity {
    private Long increaseScoreAmount = Const.START_MARKET_AMOUNT_SCORE;
    private Long increaseMoneyAmount = Const.START_MARKET_AMOUNT_MONEY;

    @Override
    public void update() {

    }
}
