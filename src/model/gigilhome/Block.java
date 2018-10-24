package model.gigilhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;

import java.util.ArrayList;

public class Block extends Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

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
