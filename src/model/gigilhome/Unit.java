package model.gigilhome;

import model.Entity;
import model.Person;
import utils.Const;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Entity{
    private final long capacity = Const.GILGE_PER_UNIT;
    private List<Person> personList = new ArrayList<>();
    private final int score = 2;

    public long getCapacity() {
        return capacity;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Unit() {
        personList.add(new Person());
        personList.add(new Person());
        personList.add(new Person());
        personList.add(new Person());
        personList.add(new Person());

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
        return personList.size()*personList.get(0).getSatisfactory()+ score;
    }
}
