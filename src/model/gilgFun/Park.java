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
public class Park extends Entity {
    private int blockId;
    private int id;
    private final Long increaseScoreAmount = Const.START_MARKET_AMOUNT_SCORE;
    private Long numOfWorkers = 0L;
    private final int score = 4;

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {
        return score;
    }
}
