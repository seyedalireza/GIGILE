package model.gigilhome;

import model.Entity;

import java.util.ArrayList;

public class Floor extends Entity{
    private ArrayList<Unit> units = new ArrayList<>();
    private final int score = 3;

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public int getScore() {
        return score;
    }

    public Floor(int unitNum) {
        for (int i = 0; i < unitNum; i++){
            units.add(new Unit());
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update() {
        units.add(new Unit());
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
            sum += unit.getPersonList().size() * unit.getPersonList().get(0).getSatisfactory();
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
