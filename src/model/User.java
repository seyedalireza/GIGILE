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
import utils.Const;

import java.util.ArrayList;

public class User {
    private String name;
    private double score = 0L;
    private double baseScore;
    private EntityCounter entityCounter = new EntityCounter();
    private Long money = Const.START_MONEY;
    private City city = new City();
    private boolean isMyTurn = false;


    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

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
        System.out.print(baseScore + this.city.calculateScore());
    }

    public void addBlock() {
        if (money >=1000) {
            Block block = new Block();
            block.setId(entityCounter.getBlockNumber());
            city.getBlocks().add(block);
            printId(block);
            money-= 1000;
            return;
        }
        System.out.println("not possible");
    }

    public void updateBlock(int blockID) {
        if (city.getBlock(blockID).getLevel() <= 2) {
            if (money >= (500 ^ (city.getBlock(blockID).getLevel()))) {
                Block block = city.getBlock(blockID);
                block.setLevel(block.getLevel() + 1);
                block.setCapacity(block.getCapacity() + 5);
                money -= (500 ^ (city.getBlock(blockID).getLevel()));
                return;
            }
        }
        System.out.println("not possible");
    }

    public void addHome(int floorSize , int blockId , int unitSize) {
        if (hasCapacityBlock(blockId)) {
            if (floorSize >= 3 && floorSize <= 6 && unitSize >= 1 && unitSize <= 4) {
                int cost = (floorSize * unitSize) * 100 + floorSize * 300 + 700;
                if (money >= cost) {
                    money -= cost;
                    Home home = new Home(floorSize, unitSize);
                    home.setId(city.getBlock(blockId).getEntityCounter().getHomeNumber());
                    printId(home);
                    city.getBlock(blockId).getEntities().add(home);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }
    public void updateHome(int unitId , int blockId , boolean unit , boolean floor) {
        Home home = getHome(blockId, unitId);
        if(floor && unit && home.getFloorNum() < 6 && home.getUnitNum() < 4) {
            if (money >= (home.getFloorNum()*50+ home.getUnitNum() + 300)) {
                home.increaseFloor(1);
                home.increaseUnit(1);
                return;
            }
        } else if (floor && home.getFloorNum() < 6 ) {
            if (money >= (home.getUnitNum() + 300)) {
                home.increaseFloor(1);
                return;
            }
        } else if (unit && home.getUnitNum() < 4) {
            if (money >= (home.getFloorNum()*50)) {
                home.increaseUnit(1);
                return;
            }
        }
            System.out.println("not possible");
    }

    private Home getHome(int blockId,int id) {
        Block block = city.getBlock(blockId);
        for (Entity entity : block.getEntities()) {
                if (entity instanceof  Home) {
                    if (entity.getId() == id ) {
                        return (Home) entity;
                    }
                }
            }
            return null;
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
                market.setId(city.getBlock(blockId).getEntityCounter().getMarketNumber());
                printId(market);
                Block block = city.getBlock(blockId);
                block.getEntities().add(market);
                changeSatisfactory(block, 1.2);
                return;
            }
        }
        }
        System.out.println("not possible");

    }

    private void changeSatisfactory(Block block, double percent) {
        for (Entity entity: block.getEntities()) {
            if (entity instanceof Home) {
                for (Person person: ((Home)entity).getPeople())
                    person.setSatisfactory(person.getSatisfactory() * percent);
            }
        }
    }

    public void addPark(int blockId) {
        if (hasCapacityBlock(blockId)) {
            ArrayList<Person> freePersons = getFreePersons();
            if (money >= 500) {
                money -= 500;
                Park park = new Park();
                park.setId(city.getBlock(blockId).getEntityCounter().getParkNumber());
                printId(park);
                Block block = city.getBlock(blockId);
                block.getEntities().add(park);
                changeSatisfactory(block, 1.1);
                return;
            }
        }
        System.out.println("not possible");
    }

    public boolean hasCapacityBlock(int blockId) {
        if (city.getBlock(blockId) == null) {return false;}
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
                    army.setId(city.getBlock(blockId).getEntityCounter().getArmyNumber());
                    printId(army);
                    city.getBlock(blockId).getEntities().add(army);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }
    private void printId(Entity entity) {
        System.out.println(entity.id);
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
                    defender.setId(city.getBlock(blockId).getEntityCounter().getDefenderNumber());
                    city.getBlock(blockId).getEntities().add(defender);
                    printId(defender);
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

    public void increaseMoney(int value) {
        this.money += value;
    }
}
