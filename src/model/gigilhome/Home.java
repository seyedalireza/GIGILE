package model.gigilhome;

import model.Entity;
import model.Person;
import utils.Const;

import java.util.ArrayList;

public class Home extends Entity {
    private int blockId;

    public Home(int floorNum, int unitNum) {
        this.floorNum = floorNum;
        this.unitNum = unitNum;
        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(unitNum));
        }
    }

    public void increaseFloor(int x) {
        this.floorNum += x;
        for (int i = 0; i < x; i++) {
            this.floors.add(new Floor(unitNum));
        }
    }

    public void increaseUnit(int x) {
        this.unitNum += x;
        for (Floor floor : floors) {
            floor.increaseUnit(x);
        }
    }

    private int floorNum = 0;
    private int unitNum = 0;
    private ArrayList<Floor> floors = new ArrayList<>();
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
        double homeScore = personScore * 3 + unitScore * 2 + floorScore + score;
        return homeScore + floorScore + unitScore + personScore;
    }

    private double calculatePersonScore(){
        double sum = 0;
        for (Floor floor: floors){
            for (Unit unit: floor.getUnits())
                sum += unit.getPersonList().size() * unit.getPersonList().get(0).getSatisfactory();
        }
        return sum;
    }

    private double calculateUnitScore() {
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

    public ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();
        for (Floor floor : floors)
            for (Unit unit : floor.getUnits())
                for (Person person : unit.getPersonList())
                    people.add(person);
        return people;
    }
}
