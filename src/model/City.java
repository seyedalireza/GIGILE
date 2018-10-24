package model;

import model.gigilhome.Block;
import model.gilgArmy.Army;

import java.util.ArrayList;

public class City {
    private ArrayList<Block> blocks = new ArrayList<>();

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void remove(int blockId){
        Block block = this.getBlock(blockId);
        this.getBlocks().remove(block);
    }

    public Block getBlock(int id){
        for (Block block: blocks){
            if (block.getId() == id){
                return block;
            }
        }
        return null;
    }

    public void attack(User opponent, int blockId) {
        Army army = null;
        for (Block block: blocks) {
            for (Entity entity: block.getEntities())
                if (entity instanceof Army)
                    army = (Army) entity;
        }
        assert army != null;
        army.attack(opponent, blockId);
    }

    public void loot(User opponent, int blockId){
        Army army = null;
        for (Block block: blocks) {
            for (Entity entity: block.getEntities())
                if (entity instanceof Army)
                    army = (Army) entity;
        }
        assert army != null;
        army.loot(opponent, blockId);
    }

    public double calculateScore(){
        double totalScore = 0;
        for (Block block: blocks) {
            totalScore += block.calculateScore();
        }
        return totalScore;
    }
}
