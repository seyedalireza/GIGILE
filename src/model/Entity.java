package model;

public abstract class Entity {
    protected Long cost;
    private int level;

    public abstract void update();
    public abstract void remove(int id);
    public abstract double calculateScore();
}
