package model.gilgArmy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;
import model.User;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Army extends Entity {
    private int blockId;
    private int id;
    private int level = 1;
    private double attackLevel = .2;
    private Long numOfWorkers = 100L;
    private final int score = 10;

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
        opponent.getCity().remove(blockId);
    }

    public void loot(User opponent, int blockId){

    }
}
