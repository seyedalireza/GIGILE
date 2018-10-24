package model.gilgArmy;

import model.Entity;
import model.Person;

import java.util.ArrayList;

public class Defender extends Entity {
    private int blockId;
    private int level = 1;
    private double defendLevel = .2;
    private int daysOfBeing = 1;

    private Long numOfWorkers = 30L;
    private final int score = 15;
    private ArrayList<Person> workers = new ArrayList<>();

    public ArrayList<Person> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Person> workers) {
        this.workers = workers;
    }

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

    public int getDaysOfBeing() {
        return daysOfBeing;
    }

    public void setDaysOfBeing(int daysOfBeing) {
        this.daysOfBeing = daysOfBeing;
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
