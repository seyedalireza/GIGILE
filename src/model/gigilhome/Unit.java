package model.gigilhome;

import lombok.Getter;
import lombok.Setter;
import model.Entity;
import utils.Const;

@Getter @Setter
public class Unit extends Entity{
    private final long capacity = Const.GILGE_PER_UNIT;
    private double update_coef = 1;
    private final int score = 2;

    public long getCapacity() {
        return capacity;
    }

    public double getUpdate_coef() {
        return update_coef;
    }

    public void setUpdate_coef(double update_coef) {
        this.update_coef = update_coef;
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
        return capacity*update_coef + score;
    }
}
