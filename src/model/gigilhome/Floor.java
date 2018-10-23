package model.gigilhome;

import lombok.Getter;
import model.Entity;

import java.util.ArrayList;

@Getter
public class Floor extends Entity{
    private ArrayList<Unit> units;
    private final int score = 3;

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {
        double personScore = calculatePersonScore();
        double unitScore = calculateUnitScore();
        return personScore * 2 + unitScore + score;
    }

    private double calculatePersonScore(){
        double sum = 0;
        for (Unit unit: units){
            sum += unit.getCapacity() * unit.getUpdate_coef();
        }
        return sum;
    }

    private double calculateUnitScore(){
        double sum = 0;
        for (Unit unit: units){
            sum += unit.calculateScore();
        }
        return sum;
    }
}
