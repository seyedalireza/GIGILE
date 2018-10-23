package controller;

import model.Entity;
import model.User;
import model.gigilhome.Block;

import java.util.Scanner;

public class Controller {
    private User[] users;

    public void cliControl(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] commandParts;
        String[] restCommandParts;

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
                    //TODO

                    break;
                case "loot":
                    //TODO

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

    }

    private void addHandler(String[] commandParts){
        switch (commandParts[0]){
            case "block":
                //TODO
                break;
            case "home":
                //TODO
                break;
            case "bazaar":
                //TODO
                break;
            case "park":
                //TODO
                break;
            case "army":
                //TODO
                break;
            case "defense":
                //TODO
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
            Block block = user.getCity().getBlocks(blockId);
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
            Block block = user.getCity().getBlocks(blockId);
            if (commandParts.length == 1){
                user.getCity().remove(blockId);
            }else{
                int unitId = Integer.parseInt(commandParts[1]);
                block.remove(unitId);
            }
        }
    }

    private void seeHandler(String[] commandParts){
        switch (commandParts[0]){
            case "gills":
                //TODO
                break;
            case "score":
                //TODO
                break;
        }
    }
}
