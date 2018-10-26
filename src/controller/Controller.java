package controller;

import model.Entity;
import model.User;
import model.gigilhome.Block;
import model.gigilhome.Home;
import model.gilgArmy.Army;
import model.gilgArmy.Defender;
import model.gilgFun.Market;
import model.gilgFun.Park;

import java.util.Scanner;

public class Controller {
    private User[] users;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void cliControl(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] commandParts;
        String[] restCommandParts;
        User opponent, currentPlayer;

        while(!command.equals("yield")){
            command = command.toLowerCase();
            commandParts = command.split(" ");
            switch (commandParts[0]){
                case "add":
                    restCommandParts = new String[commandParts.length - 1];
                    System.arraycopy(commandParts, 1, restCommandParts,
                             0, commandParts.length - 1);

                    addHandler(restCommandParts);
                    break;
                case "upgrade":
                    restCommandParts = new String[commandParts.length - 1];
                    System.arraycopy(commandParts, 1, restCommandParts,
                             0, commandParts.length - 1);
                    upgradeHandler(restCommandParts);
                    break;
                case "remove":
                    restCommandParts = new String[commandParts.length - 1];
                    System.arraycopy(commandParts, 1, restCommandParts,
                            0, commandParts.length - 1);
                    removeHandler(restCommandParts);
                    break;
                case "attack":
                    opponent = users[0];
                    currentPlayer = users[1];
                    if (opponent.isMyTurn()) {
                        opponent = users[1];
                        currentPlayer = users[0];
                    }
                    currentPlayer.getCity().attack(opponent, Integer.parseInt(commandParts[1]));
                    break;
                case "loot":
                    opponent = users[0];
                    currentPlayer = users[1];
                    if (opponent.isMyTurn()) {
                        opponent = users[1];
                        currentPlayer = users[0];
                    }
                    currentPlayer.getCity().loot(opponent, Integer.parseInt(commandParts[1]));
                    break;
                case "see":
                    restCommandParts = new String[commandParts.length - 1];
                    System.arraycopy(commandParts, 1, restCommandParts,
                            0, commandParts.length - 1);
                    seeHandler(restCommandParts);
                    break;
                case "done":
                    changeTurn();
                    break;
            }
            command = scanner.nextLine();
        }
        if (users[0].isMyTurn()){
            System.out.printf("%.2f %.2f\n",users[0].getScores(), users[1].getScores());
        }else{
            System.out.printf("%.2f %.2f\n",users[1].getScores(), users[0].getScores());
        }
    }

    private void changeTurn(){
        if (users[0].isMyTurn()){
            users[0].setMyTurn(false);
            users[1].setMyTurn(true);
            users[0].changeTurnActions();
        }else{
            users[0].setMyTurn(true);
            users[1].setMyTurn(false);
            users[1].changeTurnActions();
        }
    }

    private void addHandler(String[] commandParts){
        User current;
        current = users[0];
        if (!current.isMyTurn()) {
            current = users[1];
        }
        switch (commandParts[0]){
            case "block":
                current.addBlock();
                break;
            case "home":
                if (commandParts.length < 4) {
                    System.out.println("not possible");
                    break;
                }
                current.addHome(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[1]),
                        Integer.parseInt(commandParts[3]));
                break;
            case "bazaar":
                current.addMarket(Integer.parseInt(commandParts[1]));
                break;
            case "park":
                current.addPark(Integer.parseInt(commandParts[1]));
                break;
            case "army":
                current.addArmy(Integer.parseInt(commandParts[1]));
                break;
            case "defense":
                current.addDefender(Integer.parseInt(commandParts[1]));
                break;
        }
    }

    private void upgradeHandler(String[] commandParts){
        int blockId = Integer.parseInt(commandParts[0]);
        User user = users[0];
        if (!user.isMyTurn())
            user = users[1];
        if (user.getCity().getBlock(blockId) == null)
            System.out.println("not possible");
        else{
            Block block = user.getCity().getBlock(blockId);
            if (commandParts.length == 1){
                user.updateBlock(blockId);
            }else{
                int unitId = Integer.parseInt(commandParts[1]);
                Entity entity = block.getEntityByID(unitId);
                if (entity instanceof Home) {
                    boolean unit = false;
                    boolean floor = false;
                    if (commandParts[2].equals("unit") || (commandParts.length > 3 && commandParts[3].equals("unit")))
                        unit = true;
                    if (commandParts[2].equals("floor") || (commandParts.length > 3 && commandParts[3].equals("floor")))
                        floor = true;
                    user.updateHome(unitId, blockId, unit, floor);
                }
                else if (entity instanceof Market) {
                    user.updateMarket(blockId, (Market) entity);
                }
                else if (entity instanceof Defender) {
                    user.updateDefender(blockId, (Defender) entity);
                }
                else if (entity instanceof Army) {
                    user.updateArmy(blockId, (Army) entity);
                }
            }
        }
    }

    private void removeHandler(String[] commandParts){
        int blockId = Integer.parseInt(commandParts[0]);
        User user = users[0];
        if (!user.isMyTurn())
            user = users[1];
        if (user.getCity().getBlock(blockId) == null)
            System.out.println("not possible");
        else{
            Block block = user.getCity().getBlock(blockId);
            if (commandParts.length == 1){
                user.removeBlock(blockId);
            }else{
                int unitId = Integer.parseInt(commandParts[1]);
                Entity entity = block.getEntityByID(unitId);
                if (entity instanceof Home) {
                    System.out.println("not possible");
                }
                else if (entity instanceof Market) {
                    user.removeMarket(blockId, (Market) entity);
                }
                else if (entity instanceof Defender) {
                    user.removeDefender(blockId, (Defender) entity);
                }
                else if (entity instanceof Army) {
                    user.removeArmy(blockId, (Army) entity);
                } else if (entity instanceof Park) {
                    user.removePark(blockId, (Park) entity);
                }
            }
        }



//        int blockId = Integer.parseInt(commandParts[0]);
//        User user = users[0];
//        if (!user.isMyTurn())
//            user = users[1];
//        if (user.getCity().getBlock(blockId) == null)
//            System.out.println("not possible");
//        else{
//            Block block = user.getCity().getBlock(blockId);
//            if (commandParts.length == 1){
//                user.getCity().remove(blockId);
//            }else{
//                int unitId = Integer.parseInt(commandParts[1]);
//                block.remove(unitId);
//            }
//        }
    }

    private void seeHandler(String[] commandParts){
        User current;
        switch (commandParts[0]){
            case "gills":
                current = users[0];
                if (!current.isMyTurn()){
                    current = users[1];
                }
                current.seeGills();
                break;
            case "score":
                current = users[0];
                if (!current.isMyTurn()) {
                    current = users[1];
                }
                current.seeScore();
                break;
        }
    }
}
