package model.gilgFun;

import model.Entity;
import utils.Const;

public class Market extends Entity {
    private int blockId;
    private int id;
    private Long increaseScoreAmount = Const.START_MARKET_AMOUNT_SCORE;
    private Long numOfWorkers = 50L;
    private int level = 1;
    private final int score = 5;

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

    public void setIncreaseScoreAmount(Long increaseScoreAmount) {
        this.increaseScoreAmount = increaseScoreAmount;
    }

    public Long getNumOfWorkers() {
        return numOfWorkers;
    }

    public void setNumOfWorkers(Long numOfWorkers) {
        this.numOfWorkers = numOfWorkers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
