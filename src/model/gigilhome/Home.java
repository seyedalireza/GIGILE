package model.gigilhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;
import utils.Const;

import java.util.ArrayList;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Home extends Entity {
    private int blockId;
    private int id;
    private Long capacity = Const.START_HOME_CAPACITY;
//    private Long updateIncrease = Const.START_HOME_UPDATE_INCREASE;
    private ArrayList<Floor> floors = new ArrayList<>();
    private final int score = 10;

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {
        double personScore = calculatePersonScore();
        double unitScore = calculateUnitScore();
        double floorScore = calculateFloorScore();

        return personScore * 3 + unitScore * 2 + floorScore +
    }

    private double calculatePersonScore(){
        double sum = 0;
        for (Floor floor: floors){
            for (Unit unit: floor.getUnits())
                sum += unit.getCapacity() * unit.getUpdate_coef();
        }
        return sum;
    }

    private double calculateUnitScore(){
        double sum = 0;
        for (Floor floor: floors){
            for (Unit unit: floor.getUnits())
                sum += unit.calculateScore();
        }
        return sum;
    }

    private double calculateFloorScore(){
        double sum = 0;
        for (Floor floor: floors){
            sum += floor.calculateScore();
        }
        return sum;
    }
}
