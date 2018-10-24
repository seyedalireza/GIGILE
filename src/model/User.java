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
        System.out.printf("%.2f", baseScore + this.city.calculateScore());
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

    public void removeBlock(int blockId) {
        if (money >=1000) {
            if (city.getBlock(blockId).getId() == (entityCounter.getBlockNumber()-1)) {
                entityCounter.setBlockNumber(city.getBlock(blockId).getId() - 1);
            }
            city.getBlocks().remove(city.getBlock(blockId));
            money+= 500;
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
                Market market = new Market();
                for (int i = 0; i < 50; i++) {
                    freePersons.get(i).work();
                    market.getWorkers().add(freePersons.get(i));

                }
                market.setId(city.getBlock(blockId).getEntityCounter().getMarketNumber());
                printId(market);
                Block block = city.getBlock(blockId);
                block.getEntities().add(market);
                changeSatisfactory(block, market.getIncreaseScoreAmount());
                return;
            }
        }
        }
        System.out.println("not possible");

    }

    public void updateMarket(int blockId , Market market) {
        ArrayList<Person> freePersons = getFreePersons();
        if (market.getLevel() < 3) {
            if (freePersons.size() >= 20) {
                for (int i = 0; i < 20; i++) {
                    freePersons.get(i).work();
                    market.getWorkers().add(freePersons.get(i));

                }
                if (money >= (market.getLevel() + 1) * 5000) {
                    money -= (market.getLevel() + 1) * 5000;
                    Block block = getCity().getBlock(blockId);
                    market.setLevel(market.getLevel() + 1);
                    market.setIncreaseScoreAmount(1 + .2 * market.getLevel());
                    changeSatisfactory(block, (1 + market.getLevel() * .2) / (1 + (market.getLevel() - 1) * .2));
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
                    Army army = new Army(this);
                    for (int i = 0; i < 100; i++) {
                        freePersons.get(i).work();
                        army.getWorkers().add(freePersons.get(i));

                    }
                    army.setId(city.getBlock(blockId).getEntityCounter().getArmyNumber());
                    printId(army);
                    city.getBlock(blockId).getEntities().add(army);
                    return;
                }
            }
        }
        System.out.println("not possible");
    }

    public void updateArmy(int blockId , Army army) {
        ArrayList<Person> freePersons = getFreePersons();
        if (army.getLevel() < 5) {
            if (freePersons.size() >= 10) {
                if (money >= 20000) {
                    for (int i = 0; i < 10; i++) {
                        freePersons.get(i).work();
                        army.getWorkers().add(freePersons.get(i));
                    }
                    army.setLevel(army.getLevel()+1);
                    army.setAttackLevel(army.getLevel() * .2);
                    money -= 20000;//                    Block block = getCity().getBlock(blockId);
//                    market.setLevel(market.getLevel() + 1);
//                    market.setIncreaseScoreAmount(1 + .2 * market.getLevel());
//                    changeSatisfactory(block, (1 + market.getLevel() * .2) / (1 + (market.getLevel() - 1) * .2));
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

    public void updateDefender(int blockId , Defender defender) {
        ArrayList<Person> freePersons = getFreePersons();
        if (defender.getLevel() < 5) {
            if (freePersons.size() >= 0) {
                if (money >= 5000) {
                    for (int i = 0; i < 0; i++) {
                        freePersons.get(i).work();
                    }
                    defender.setLevel(defender.getLevel()+1);
                    defender.setDefendLevel(defender.getLevel() * .2);
                    money -= 5000;
//                    Block block = getCity().getBlock(blockId);
//                    market.setLevel(market.getLevel() + 1);
//                    market.setIncreaseScoreAmount(1 + .2 * market.getLevel());
//                    changeSatisfactory(block, (1 + market.getLevel() * .2) / (1 + (market.getLevel() - 1) * .2));
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

    public void increaseMoney(int value) {
        this.money += value;
    }
}
