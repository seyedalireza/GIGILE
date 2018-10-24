package model.gilgFun;

import model.Entity;
import utils.Const;

public class Park extends Entity {
    private int blockId;
    private final Long increaseScoreAmount = Const.START_MARKET_AMOUNT_SCORE;
    private Long numOfWorkers = 0L;
    private final int score = 4;

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getIncreaseScoreAmount() {
        return increaseScoreAmount;
    }

    public Long getNumOfWorkers() {
        return numOfWorkers;
    }

    public void setNumOfWorkers(Long numOfWorkers) {
        this.numOfWorkers = numOfWorkers;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {
        return score;
    }
}
