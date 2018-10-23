package model;

public abstract class Entity {
    protected Long cost;
    private int level;

    public abstract void update();
    public abstract double calculateScore();
}
