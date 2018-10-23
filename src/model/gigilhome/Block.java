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

    @Override
    public void update() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {

    }

    public Entity getEntityByID(int id){
        for (Entity entity: entities){
            if (entity.getId() == id){
                return entity;
            }
        }
        return null;
    }
}
