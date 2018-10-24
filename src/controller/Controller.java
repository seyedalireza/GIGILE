package controller;

import model.Entity;
import model.User;
import model.gigilhome.Block;

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

        while(!command.equals("Yield")){
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
        }
    }

    private void changeTurn(){
        if (users[0].isMyTurn()){
            users[0].setMyTurn(false);
            users[1].setMyTurn(true);
        }else{
            users[0].setMyTurn(true);
            users[1].setMyTurn(false);
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
                block.update();
            }else{
                int unitId = Integer.parseInt(commandParts[1]);
                Entity entity = block.getEntityByID(unitId);
                entity.update();
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
                user.getCity().remove(blockId);
            }else{
                int unitId = Integer.parseInt(commandParts[1]);
                block.remove(unitId);
            }
        }
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
                if (!current.isMyTurn()){
                    current = users[1];
                }
                current.seeScore();
                break;
        }
    }
}
