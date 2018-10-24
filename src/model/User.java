package model;

import controller.EntityCounter;
import model.gigilhome.Block;
import model.gigilhome.Floor;
import model.gigilhome.Home;
import model.gigilhome.Unit;
import model.gilgArmy.Army;
import model.gilgArmy.Defender;
import model.gilgFun.Market;
import model.gilgFun.Park;
import org.w3c.dom.css.Counter;
import utils.Const;

import java.util.ArrayList;

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
            city.getBlocks().add(block);
            money-= 1000;
            return;
        }
        System.out.println("not possible");
    }

    public void addHome(int floorSize , int blockId , int unitSize) {
        if (hasCapacityBlock(blockId)) {
            if (floorSize >= 3 && floorSize <= 6 && unitSize >= 3 && unitSize <= 4) {
                int cost = (floorSize * unitSize) * 100 + floorSize * 300 + 700;
                if (money >= cost) {
                    money -= cost;
                    Home home = new Home(floorSize, unitSize);
                    home.setId(entityCounter.getHomeNumber());
                    city.getBlock(blockId).getEntities().add(home);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }

    public void addMarket(int blockId) {
        if(hasCapacityBlock(blockId)) {
        ArrayList<Person> freePersons = getFreePersons();
        if(freePersons.size() >= 50) {
            if (money >= 6000) {
                money -= 6000;
                for (int i = 0; i < 50; i++) {
                    freePersons.get(i).work();
                }
                Market market = new Market();
                market.setId(entityCounter.getMarketNumber());
                city.getBlock(blockId).getEntities().add(market);
                return;
            }
        }
        }
        System.out.println("not possible");

    }

    public void addPark(int blockId) {
        if (hasCapacityBlock(blockId)) {
            ArrayList<Person> freePersons = getFreePersons();
            if (money >= 500) {
                money -= 500;
                Park park = new Park();
                park.setId(entityCounter.getParkNumber());
                city.getBlock(blockId).getEntities().add(park);
                return;
            }
        }
        System.out.println("not possible");
    }

    public boolean hasCapacityBlock(int blockId) {
        return city.getBlock(blockId).getEntities().size() < city.getBlock(blockId).getCapacity();
    }

    public void addArmy(int blockId) {
        if(hasCapacityBlock(blockId)) {
            ArrayList<Person> freePersons = getFreePersons();
            if(freePersons.size() >= 100) {
                if (money >= 15000) {
                    money -= 15000;
                    for (int i = 0; i < 100; i++) {
                        freePersons.get(i).work();
                    }
                    Army army = new Army(this);
                    army.setId(entityCounter.getArmyNumber());
                    city.getBlock(blockId).getEntities().add(army);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }

    public void addDefender(int blockId) {
        if(hasCapacityBlock(blockId)) {
            ArrayList<Person> freePersons = getFreePersons();
            if(freePersons.size() >= 30) {
                if (money >= 10000) {
                    money -= 10000;
                    for (int i = 0; i < 30; i++) {
                        freePersons.get(i).work();
                    }
                    Defender defender = new Defender();
                    defender.setId(entityCounter.getDefenderNumber());
                    city.getBlock(blockId).getEntities().add(defender);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }

    public ArrayList<Person> getFreePersons() {
        ArrayList<Person>  people = new ArrayList<>();
        for (Block block : city.getBlocks()) {
            for (Entity entity : block.getEntities()) {
                if (entity instanceof  Home) {
                    for (Floor floor : ((Home) entity).getFloors()) {
                        for (Unit unit : floor.getUnits()) {
                            for (Person person : unit.getPersonList()) {
                                if (!person.isWorker()) {
                                    people.add(person);
                                }
                            }
                        }
                    }
                }
            }
        }
        return people;
    }

    public ArrayList<Person> getWorkerPersons() {
        ArrayList<Person>  people = new ArrayList<>();
        for (Block block : city.getBlocks()) {
            for (Entity entity : block.getEntities()) {
                if (entity instanceof  Home) {
                    for (Floor floor : ((Home) entity).getFloors()) {
                        for (Unit unit : floor.getUnits()) {
                            for (Person person : unit.getPersonList()) {
                                if (person.isWorker()) {
                                    people.add(person);
                                }
                            }
                        }
                    }
                }
            }
        }
        return people;
    }
}
