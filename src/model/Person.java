package model;

//not needed object
public class Person extends Entity {
    private double satisfactory = 1;
    private int daysOfWorking = 0;
    private boolean isWorker = false;

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public void work(){
        isWorker = true;
    }

    public void getFired(){
        isWorker = false;
        daysOfWorking = 0;
    }

    public int getDaysOfWorking() {
        return daysOfWorking;
    }

    public void setDaysOfWorking(int daysOfWorking) {
        this.daysOfWorking = daysOfWorking;
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

    public double getSatisfactory() {
        return satisfactory;
    }

    public void setSatisfactory(double satisfactory) {
        this.satisfactory = satisfactory;
    }
}
