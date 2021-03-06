package model.gigilhome;

import controller.EntityCounter;
import model.Entity;

import java.util.ArrayList;

public class Block extends Entity {
    private int capacity = 15;
    private EntityCounter counter = new EntityCounter();
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
        double totalScore = 0;
        for (Entity entity: entities) {
            totalScore += entity.calculateScore();
        }
        return totalScore;
    }

    public Entity getEntityByID(int id){
        for (Entity entity: entities){
            if (entity.getId() == id){
                return entity;
            }
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EntityCounter getEntityCounter() {
        return counter;
    }
}
