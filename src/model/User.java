package model;

import controller.EntityCounter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.gigilhome.Block;
import model.gigilhome.Home;
import org.w3c.dom.css.Counter;
import utils.Const;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private double score = 0L;
    private double baseScore;
    private EntityCounter entityCounter = new EntityCounter();

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    private Long money = Const.START_MONEY;
    private City city = new City();
    private boolean isMyTurn = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public void claculateScore() {

    }

    public void seeGills() {
        System.out.println(this.money);
    }

    public void seeScore(){
        System.out.println(this.city.calculateScore());
    }

    public void addBlock() {
        if (money >=1000) {
            Block block = new Block();
            block.setId(entityCounter.getBlockNumber());
            money-= 1000;
        }
        System.out.println("not possible");
    }

    public void addHome(int floorSize , int blockId , int unitSize) {
        int cost = (floorSize*unitSize)*100+floorSize*300+700;
        if (money >= cost) {
            money -= cost;
            city.getBlock(blockId).getEntities().add(new Home(floorSize , unitSize));
        }
        System.out.println("not possible");
    }

    public void addMarket() {

    }

    public void addPark() {

    }

    public void addArmy() {

    }

    public void addDefender() {

    }
}
