package model.gilgFun;

import model.Entity;
import model.Person;
import utils.Const;

import java.util.ArrayList;

public class Park extends Entity {
    private int blockId;
    private final double increaseScoreAmount = 1.1;
    private Long numOfWorkers = 0L;
    private int daysOfBeing = 1;

    private final int score = 4;


    public int getBlockId() {
        return blockId;
    }

    public int getDaysOfBeing() {
        return daysOfBeing;
    }

    public void setDaysOfBeing(int daysOfBeing) {
        this.daysOfBeing = daysOfBeing;
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

    public double getIncreaseScoreAmount() {
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
        return Math.pow(score, daysOfBeing);
    }
}
