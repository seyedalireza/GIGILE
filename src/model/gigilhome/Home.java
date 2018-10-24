package model.gigilhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;
import utils.Const;

import java.util.ArrayList;

public class Home extends Entity {
    private int blockId;

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

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public int getScore() {
        return score;
    }

    private int id;
    private int floorNum = 0;
    private int unitNum = 0;
    private ArrayList<Floor> floors = new ArrayList<>();
    private final int score = 10;

    @Override
    public void update() {
        floors.add(new Floor(unitNum));
        floorNum++;
    }

    @Override
    public void remove(int id) {
        //do nothing
    }

    @Override
    public double calculateScore() {
        double personScore = calculatePersonScore();
        double unitScore = calculateUnitScore();
        double floorScore = calculateFloorScore();
        return personScore * 3 + unitScore * 2 + floorScore + score;
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
