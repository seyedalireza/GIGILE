package model.gigilhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block extends Entity {
    private int id;
    private ArrayList<Entity> entities = new ArrayList<>();
//    private boolean isDestroy = false;

    @Override
    public void update() {

    }

    @Override
    public double calculateScore() {

    }
}
