package model.gigilhome;

import model.Entity;
import utils.Const;

public class Unit extends Entity{
    private final long capacity = Const.GILGE_PER_UNIT;
    private final int score = 2;

    public long getCapacity() {
        return capacity;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update() {
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {
        return 0;
    }
}
