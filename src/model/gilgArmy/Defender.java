package model.gilgArmy;

import model.Entity;

public class Defender extends Entity {
    private int blockId;
    private int id;
    private int level = 1;
    private double defendLevel = .2;
    private Long numOfWorkers = 30L;
    private final int score = 15;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getDefendLevel() {
        return defendLevel;
    }

    public void setDefendLevel(double defendLevel) {
        this.defendLevel = defendLevel;
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
