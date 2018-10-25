import java.util.Scanner;
import java.util.ArrayList;


enum City {
    city1,city2;
    public static final int INIT_BLOCK_COST = 1000;
    public static final int REMOVE_BLOCK_COST = 500;
    public static final int INIT_MONEY= 30*1000;
    private Block armyBlock=null;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private int money=0;
    {
        money = INIT_MONEY;
    }
    public Block getArmyBlock(){
        return armyBlock;
    }
    Block getBlock(int blockId){
        if(blockId>=blocks.size())return null;
        return blocks.get(blockId);
    }
    public double getScore(){
        double sum=0.0;
        for(int i=0;i<blocks.size();i++){
            sum+=blocks.get(i).getScore();
        }
        return sum;
    }
    public void updateMoney(){
        for(int i=0;i<blocks.size();i++){
            Block x=blocks.get(i);
            if (x != null)
                money+=x.getIncome();
        }
    }
    public void addMoney(int val){
        money+=val;
    }
    public int subtractMoney(int val){
        if(getMoney()<val){
            return -1;
        }
        money-=val;
        return 1;
    }
    public int addBlock(){
        if(subtractMoney(INIT_BLOCK_COST) == -1){
            return -1;
        }
        Block newBlock=new Block();
        blocks.add(newBlock);
        return blocks.size();
    }
    public int removeBlock(int blockId){
        Block block=getBlock(blockId);
        if(block==null)return -1;
        if(armyBlock==block){
            armyBlock=null;
        }
        addMoney(REMOVE_BLOCK_COST);
        blocks.set(blockId,null);
        return 1;
    }

    public int upgradeBlock(int blockId){
        Block block=getBlock(blockId);
        if(block==null)return -1;
        int cost=block.upgradeMoney();
        if(getMoney()<cost){
            return -1;
        }
        if(block.upgrade()==-1)return -1;
        subtractMoney(cost);
        return 1;
    }
    public int addElement(int blockId,Element element){ //IMPOSSIBLE == -1
        Block block=blocks.get(blockId);
        if(block==null)return -1;
        if(element instanceof Army){
            if(armyBlock!=null)return -1;
        }
        int answer=block.addElement(element);
        if(answer==-1)return -1;
        if(element instanceof Army){
            armyBlock=block;
        }
        return answer;
    }
    public int removeElement(int blockId,int elementId) { //Impossible == -1 else 1
        Block block=getBlock(blockId);
        if(block==null)return -1;
        int answer=block.removeElement(elementId);//khodesh money ro ok mikone
        if(answer==-1)return -1;
        if(answer==2){//element is_A Army.
            armyBlock=null;
        }
        return 1;
    }

    public int upgradeElement(int blockId,int elementId, int floor, int unit){
        Block block=getBlock(blockId);
        if(block==null){
            return -1;
        }
        return block.upgradeElement(elementId, floor, unit); // saberi upgrade elementesh age element id nabood -1 bede
    }
    public int getMoney(){
        return money;
    }

}

public class Main2 {
    private static City[] player;

    static {
        player=new City[2];
        player[0]=City.city1;
        player[1]=City.city2;
    }
    static int day=0;

    static int getDay()
    {
        return day;
    }
    public static int getTurn()

    {
        return (day%2);
    }
    public static City getCity()
    {
        return player[getTurn()];
    }
    public static int attack(int blockId)// age nmishod -1 mide vgrna 1
    {
        return 1;
    }
    public static int loot(int blockId)// age nmishod -1 mide vgrna 1
    {
        City myCity=player[getTurn()],enemyCity=player[1-getTurn()];
        if(enemyCity.getBlock(blockId)==null || enemyCity.getBlock(blockId).getDefenceId()!=-1)return -1;
        myCity.addMoney(enemyCity.getBlock(blockId).numberOfElements()*500);
        return 1;
    }
    public void yield(City city)
    {

    }
    public static void endDay()
    {

        //for(int i=0;i<2;i++) {
        player[getTurn()].updateMoney();
        // }
        day++;
    }
    public static void check(int x, int give) {
        if (x == -1) {
            System.out.println("not possible");
        } else {
            if (give == 1)
                System.out.println(x);
        }
    }
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] queries = line.split(" ");
            for (int j = 0; j < (int) queries.length; ++j) {
                if (queries[j].matches("[a-zA-Z]+")) {
                    queries[j] = queries[j].toLowerCase();
                }
            }
            if (queries[0].equals("yield")) {
                System.out.format("%.2f %.2f\n", player[getTurn()].getScore(), player[getTurn() ^ 1].getScore());
                return;
            }
            if (queries[0].equals("done")) {
                endDay();
                continue;
            }
            if (queries[0].equals("attack")) {
                if (player[getTurn()].getMoney() < 5000) {
                    check(-1, 0);
                } else {
                    check(attack(Integer.parseInt(queries[1])), 0);
                }
                continue;
            }
            if (queries[0].equals("loot")) {
                check(loot(Integer.parseInt(queries[1])), 0);
                continue;
            }
            if (queries[0].equals("see")) {
                if (queries[1].equals("score")) {
                    System.out.format("%.2f\n", player[getTurn()].getScore());
                } else {
                    System.out.println(player[getTurn()].getMoney());
                }
                continue;
            }
            if (queries[0].equals("add")) {
                if (queries[1].equals("block")) {
                    check(player[getTurn()].addBlock(), 1);
                } else if (queries[1].equals("home")) {
                    int blockId = Integer.parseInt(queries[2]);
                    --blockId;
                    int numFloor = Integer.parseInt(queries[3]);
                    int numUnit = Integer.parseInt(queries[4]);
                    if (numUnit > 4 || numUnit < 1 || numFloor > 6 || numFloor < 3) {
                        check(-1, 0);
                        continue;
                    }
                    int use = Home.getBuildCost(numFloor, numUnit);
                    check(use > player[getTurn()].getMoney() ? -1 :
                            player[getTurn()].addElement(blockId, (Element) new Home(numFloor, numUnit)), 1);

                } else {
                    int blockId = Integer.parseInt(queries[2]);
                    --blockId;
                    if (queries[1].equals("bazaar")) {
                        int use = (new Bazaar()).getBuildCost();
                        check(use > player[getTurn()].getMoney() ? -1 : player[getTurn()].addElement(blockId, (Element) new Bazaar()), 1);
                    } else if (queries[1].equals("army")) {
                        int use = (new Army()).getBuildCost();
                        check(use > player[getTurn()].getMoney() ? -1 : player[getTurn()].addElement(blockId, (Element) new Army()), 1);
                    } else if (queries[1].equals("defense")) {
                        int use = (new Defence()).getBuildCost();
                        check(use > player[getTurn()].getMoney() ? -1 : player[getTurn()].addElement(blockId, (Element) new Defence()), 1);
                    }
                }
                continue;
            }
            if (queries[0].equals("upgrade")) {

                int blockId = Integer.parseInt(queries[1]);
                --blockId;
                if (queries.length < 3) {
                    check(player[getTurn()].upgradeBlock(blockId), 0);
                } else {
                    int unitId = Integer.parseInt(queries[2]);
                    --unitId;
                    int floor = 0, unit = 0;
                    for (String x : queries) {
                        if (x.equals("unit")) {
                            unit = 1;
                        }
                        if (x.equals("floor")) {
                            floor = 1;
                        }
                    }
                    check(player[getTurn()].upgradeElement(blockId, unitId, floor, unit), 0);

                }
                continue;
            }
            if (queries[0].equals("remove")) {
                int blockId = Integer.parseInt(queries[1]);
                --blockId;
                if (queries.length < 3) {
                    check(player[getTurn()].removeBlock(blockId), 0);
                } else {
                    int unitId = Integer.parseInt(queries[2]);
                    --unitId;
                    check(player[getTurn()].removeElement(blockId, unitId), 0);
                }
                continue;
            }
        }

    }

}
class Element {
    private int level, income, blockId;
    private int dayBuilt;
    static int LEVEL_LIMIT = 3;
    static int CONST_COEFF = 0;
    static int BUILD_COST = 0;
    static int REMOVE_COST = 0;
    static int UPGRADE_COST = 0;

    public int NEEDED_UNITS = 0;
    public int NEEDED_UNITS_STEP = 0;
    public int getConstCoeff() { return CONST_COEFF; }
    public int getBuildCost() {  return BUILD_COST; }
    public int getRemoveCost() {  return REMOVE_COST; }
    public int getUpgradeCost() {  return UPGRADE_COST; }
    public int getLevelLimit() {  return LEVEL_LIMIT; }
    public int getNeededUnits() { return 0;}
    public int getNeededUnitsStep() { return 0;}
    Element() {
        this.setLevel(1);
        this.dayBuilt = Main2.getDay();
    }
    public void setIncome(int newIncome) {
        this.income = newIncome;
    }
    public int getIncome() {
        return this.numberOfPeople() * 100 + this.income;
    }
    public int setLevel(int newLevel) {
        if (newLevel >= 1 && newLevel <= getLevelLimit()) {
            this.level = newLevel;

            return 0;
        } else {
            // error
            return -1;
        }
    }
    public int getLevel() { return this.level; }
    public int levelUp() { return setLevel(getLevel() + 1); }
    public void setBlockId(int newBlockId) {
        this.blockId = newBlockId;
    }
    public int getBlockId() { return this.blockId; }
    public int getDayBuilt() { return this.dayBuilt; }
    public double score() {
        return Math.pow(this.getConstCoeff(), (Main2.getDay() - getDayBuilt()) + 1);
    }
    public double score(double aCoeff) { return 0; }


    public void updateIncome() {
        setIncome(getIncome());
    }
    public int numberOfPeople() {
        return NEEDED_UNITS_STEP * (this.getLevel() - 1) + NEEDED_UNITS;
    }
    public double getScoreOfPerson() {
        return 1.;
    }
}

class Block
{
    private final int INITIAL_MAX_ELEMENTS=15,UPGRADE_MAX_ELEMENTS=5;
    private ArrayList<Element> elements = new ArrayList<Element>();
    private int level,defenceId;
    Block()
    {
        level=1;
        defenceId=-1;
    }
    public int numberOfMaxElements()
    {
        return INITIAL_MAX_ELEMENTS+(level-1)*UPGRADE_MAX_ELEMENTS;
    }
    public int numberOfElements()
    {
        int cntElement=0;
        for(Element element:elements)
        {
            if(element==null)continue;
            cntElement++;
        }
        return cntElement;
    }
    public int getIncome()
    {
        int income=0;
        for(Element element:elements)
        {
            if(element==null)continue;
            income+=element.getIncome();
        }
        return income;
    }
    public double getScore()
    {
        double score=0;
        for(Element element:elements)
        {
            if(element==null)continue;
            if(element instanceof Home)
                score+=((Home) element).score(getScoreOfPerson());
            else
                score+=element.score();

        }
        return score;
    }
    public double getScoreOfPerson()
    {
        double score=1.;
        for(Element element:elements)
        {
            if(element==null)continue;
            score*=element.getScoreOfPerson();
        }
        return score;
    }
    public int numberOfUnusedPeople()
    {
        int unusedPeople=0;
        for(Element element:elements)
        {
            if(element==null)continue;

            if(element instanceof Home)
            {
                unusedPeople+=((Home) element).numberOfPeople();
            }
            else
            {
                unusedPeople-=element.numberOfPeople();
            }
        }
        return unusedPeople;
    }
    public int addElement(Element element)// age nmishod -1 mide vgrna 1
    {
        int buildCost;
        if(element instanceof Home)
            buildCost=((Home) element).getBuildCost(((Home) element).getNumberOfFloors(),((Home) element).getNumberOfUnits());
        else buildCost=element.getBuildCost();
        if(buildCost> Main2.getCity().getMoney())
        {
            return -1;
        }
        if(element instanceof  Defence && defenceId!=-1)
        {
            return -1;
        }
        if(elements.size()+1>numberOfMaxElements())return -1;
        {
            int unused = numberOfUnusedPeople() + (element instanceof Home ? element.numberOfPeople() : -element.numberOfPeople());
            if (unused < 0) {
                return -1;
            }
        }
        elements.add(element);
        if(element instanceof Defence)
        {
            defenceId= elements.size()-1;
        }
        Main2.getCity().subtractMoney(buildCost);
        return elements.size();
    }
    public int removeElement(int elementId)// age ghbln remove shode bod -1 age army bod 2 vgrna 1
    {
        if(elementId>=elements.size() || elements.get(elementId)==null)return -1;
        if(elementId==defenceId)defenceId=-1;
        int returnValue=1;
        Element element=elements.get(elementId);
        if(element instanceof Army)returnValue=2;
        int removeCost;
        if(element instanceof Home)
        {
            return -1;
        }
        removeCost=element.getRemoveCost();
        if(removeCost>=0)
        {
            if(Main2.getCity().subtractMoney(removeCost)==-1)
                return -1;
        }
        else
        {
            Main2.getCity().addMoney(-removeCost);
        }
        elements.set(elementId, null);
        return returnValue;
    }
    public int upgradeElement(int elementId, int floor, int unit)
    {
        if(elementId>=elements.size() || elements.get(elementId)==null)return -1;
        Element element=elements.get(elementId);

        int upgradeCost=element.getUpgradeCost();
        if (element instanceof Home) {
            upgradeCost = ((Home) element).getUpgradeCost(floor, unit);
        }
        if(upgradeCost>=0)
        {
            if(Main2.getCity().subtractMoney(upgradeCost)==-1)
                return -1;
        }
        else
        {
            Main2.getCity().addMoney(-upgradeCost);
        }
        if (element instanceof Home) {
            if (((Home) element).levelUp(floor, unit) == -1) return -1;
        } else {
            if (elements.get(elementId).levelUp() == -1) return -1;
        }
        return 1;
    }
    public int getDefenceId()
    {
        if(defenceId==-1)return -1;
        return defenceId+1;
    }
    public int upgrade()// age az 3 bishtr shod -1 vrgna 1
    {
        level++;
        if(level>3)
        {
            level=3;
            return -1;
        }
        return 1;
    }
    public int upgradeMoney()
    {
        if(level==1)return 500;
        return 500*500;
    }

}


class Home extends Element {
    private int numberOfFloors, numberOfUnits;
    public final int PEOPLE_PER_UNIT = 5;
    public final double CONST_PERSON = 1;
    public final double CONST_UNIT = 2;
    public final double CONST_FLOOR = 3;
    public final double CONST_HOME = 10;
    //   @Override
    {
        CONST_COEFF = 10;
    }

    static int getBuildCost(int floors, int units) {
        return floors * units * 100 + floors * 300 + 700;
    }
    public int getUpgradeCost(int floor, int unit) {
        return ((floor + getNumberOfFloors()) * (unit + getNumberOfUnits()) - getNumberOfUnits() * getNumberOfFloors()) * 50 + floor * 300;
    }
    public int getNumberOfFloors() { return this.numberOfFloors; }
    public int setNumberOfFloors(int newNumberOfFloors) {
        if (newNumberOfFloors >= 3 && newNumberOfFloors <= 6) {
            this.numberOfFloors = newNumberOfFloors;
            return 0;
        } else {
            return -1;
            // not possible
        }
    }
    @Override
    public void updateIncome() {
        return;
    }
    public int getNumberOfUnits() { return this.numberOfUnits; }
    public int setNumberOfUnits(int newNumberOfUnits) {
        if (newNumberOfUnits >= 1 && newNumberOfUnits <= 4) {
            this.numberOfUnits = newNumberOfUnits;
            return 0;
        } else {
            return -1;
            // not possible
        }
    }
    Home(int numberOfFloors, int numberOfUnits) {
        super();
        this.setNumberOfFloors(numberOfFloors);
        this.setNumberOfUnits(numberOfUnits);
    }
    public int levelUp(int floor, int unit) {
        if (getNumberOfFloors() + floor > 6) return -1;
        if (getNumberOfUnits() + unit > 4) return -1;

        setNumberOfFloors(getNumberOfFloors() + floor);
        setNumberOfUnits(getNumberOfUnits() + unit);
        return 0;
    }
    public double scorePerson(double aCoeff) {
        return aCoeff * CONST_PERSON;
    }
    public double scoreUnit(double aCoeff) {
        return CONST_UNIT + PEOPLE_PER_UNIT * scorePerson(aCoeff);
    }
    public double scoreFloor(double aCoeff) {
        return CONST_FLOOR + getNumberOfUnits() * scoreUnit(aCoeff) + 2 * getNumberOfUnits() * PEOPLE_PER_UNIT
                * scorePerson(aCoeff);
    }
    public double scoreHome(double aCoeff) {
        return CONST_HOME + getNumberOfFloors() * scoreFloor(aCoeff) + getNumberOfFloors() * getNumberOfUnits() * 2
                * scoreUnit(aCoeff) + numberOfPeople() * 3 * scorePerson(aCoeff);
    }
    @Override
    public double score(double aCoeff) { return
            scoreHome(aCoeff) +
                    getNumberOfFloors() * scoreFloor(aCoeff)
                    + getNumberOfFloors() * getNumberOfUnits() * scoreUnit(aCoeff) +
                    numberOfPeople() * scorePerson(aCoeff); }
    @Override
    public int numberOfPeople() {
        return PEOPLE_PER_UNIT * getNumberOfUnits() * getNumberOfFloors();
    }


}
class Bazaar extends Element {
    Bazaar() {
        super();
    }

    {
        CONST_COEFF = 5;
        BUILD_COST = 6000;
        REMOVE_COST = 500;
        NEEDED_UNITS = 50;
        NEEDED_UNITS_STEP = 20;
    }

    @Override
    public int getUpgradeCost() { return (this.getLevel() + 1) * 5000; }


    @Override
    public double getScoreOfPerson(){
        return 1. + this.getLevel() * 0.2;
    }


}
class Army extends Element {
    Army() {
        super();
    }
    //  @Override
    {
        CONST_COEFF = 10;
        BUILD_COST = 15000;
        UPGRADE_COST = 20000;
        REMOVE_COST = -10000;
        LEVEL_LIMIT = 5;
        NEEDED_UNITS = 100;
        NEEDED_UNITS_STEP = 10;
    }
    public double getAttackLevel() {
        return 0.2 * this.getLevel();
    }

}
class Defence extends Element {
    Defence() {
        super();
    }
    // @Override
    {
        LEVEL_LIMIT = 5;
        CONST_COEFF = 15;
        BUILD_COST = 10000;
        REMOVE_COST = -10000;
        UPGRADE_COST = 5000;
        NEEDED_UNITS = 30;
        NEEDED_UNITS_STEP = 0;
    }
    public double defenseLevel() {
        return this.getLevel() * 0.2;
    }

}

