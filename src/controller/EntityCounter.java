package controller;

public class EntityCounter {

    private int homeNumber = 0;
    private int floorNumber = 0;
    private int unitNumber = 0;
    private int blockNumber = 0;
    private int armyNumber = 0;
    private int defenderNumber = 0;
    private int marketNumber = 0;
    private int parkNumber = 0;
    private int personNumber;

    public EntityCounter() {
    }

    public int getHomeNumber() {
        homeNumber++;
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public int getFloorNumber() {
        floorNumber++;
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getUnitNumber() {
        unitNumber++;
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getBlockNumber() {
        blockNumber++;
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getArmyNumber() {
        armyNumber++;
        return armyNumber;
    }

    public void setArmyNumber(int armyNumber) {
        this.armyNumber = armyNumber;
    }

    public int getDefenderNumber() {
        defenderNumber++;
        return defenderNumber;
    }

    public void setDefenderNumber(int defenderNumber) {
        this.defenderNumber = defenderNumber;
    }

    public int getMarketNumber() {
        marketNumber++;
        return marketNumber;
    }

    public void setMarketNumber(int marketNumber) {
        this.marketNumber = marketNumber;
    }

    public int getParkNumber() {
        parkNumber++;
        return parkNumber;
    }

    public void setParkNumber(int parkNumber) {
        this.parkNumber = parkNumber;
    }

    public int getPersonNumber() {
        personNumber++;
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }
}
