package model;

import model.gigilhome.Block;

import java.util.ArrayList;

public class City {
    private ArrayList<Block> blocks = new ArrayList<>();

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void remove(int blockId){
        this.getBlocks().remove(this.getBlock(blockId));
    }

    public Block getBlock(int id){
        for (Block block: blocks){
            if (block.getId() == id){
                return block;
            }
        }
        return null;
    }
}
