package model.gilgArmy;

import model.Entity;
import model.Person;
import model.User;

import java.util.ArrayList;

public class Army extends Entity {
    private int blockId;
    private int level = 1;
    private double attackLevel = .2;
    private Long numOfWorkers = 100L;
    private final int score = 10;
    private User me;
    private ArrayList<Person> workers = new ArrayList<>();

    public ArrayList<Person> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Person> workers) {
        this.workers = workers;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public Army(User me) {
        this.me = me;
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

    public double getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(double attackLevel) {
        this.attackLevel = attackLevel;
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

    public void attack(User opponent, int blockId) {
        for (Entity entity : opponent.getCity().getBlock(blockId).getEntities()) {
            if (entity instanceof Defender) {
                if (entity.getLevel() >= this.level) {
                    System.out.println("not possible");
                    return;
                }
            }
        }
        me.setBaseScore(me.getBaseScore() + opponent.getCity().getBlock(blockId).calculateScore());
        opponent.getCity().remove(blockId);
    }

    public void loot(User opponent, int blockId){
        boolean hasDefender = false;
        for (Entity entity : opponent.getCity().getBlock(blockId).getEntities()) {
            if (entity instanceof Defender) {
                hasDefender = true;
                }
            }
            if (!hasDefender) {
                me.setMoney(me.getMoney() + opponent.getCity().getBlock(blockId).getEntities().size()*500);
        } else {
                System.out.println("not possible");
            }
    }
}
