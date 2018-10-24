package model;

//not needed object
public class Person extends Entity {
    private float satisfactory = 1;
    private boolean isWorker = false;

    public void work(){
        isWorker = true;
    }

    public void getFired(){
        isWorker = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {
        return 1;
    }
}
