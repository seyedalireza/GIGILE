package controller;

public class EntityCounter {

    private int blockNumber = 0;
    private int entityNumber = 0;
    private int personNumber;

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setEntityNumber(int entityNumber) {
        this.entityNumber = entityNumber;
    }

    public EntityCounter() {
    }

    public int getHomeNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getFloorNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getUnitNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getBlockNumber() {
        blockNumber++;
        return blockNumber;
    }


    public int getArmyNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getDefenderNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getMarketNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getParkNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getPersonNumber() {
        personNumber++;
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getEntityNumber() {
        return entityNumber;
    }
}
