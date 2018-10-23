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
    private int blockId;
    private int id;
    private Long increaseScoreAmount = Const.START_MARKET_AMOUNT_SCORE;
    private Long numOfWorkers = 50L;
    private int level = 1;
    private final int score = 5;

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {
        return score;
    }
}
