package model.gilgArmy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;

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

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {
        return score;
    }
}
