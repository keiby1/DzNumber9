package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lushi on 21.11.2016.
 */
public class Squad {
    private String name;
    private ArrayList<Warrior> warrior;

    public Warrior getRandomWarrior() { //получение случайного воина из отряда
        Random r = new Random();
        int ind, n = warrior.size();
        if (hasAliveWarriors())
            while (true) {
                ind = r.nextInt(n);
                if (warrior.get(ind).isAlive()) {
                    return warrior.get(ind);
                }
            }
        else
            return null;
    }

    public void createSquad(ObservableList<String> squad, String namesquad) {
        WarriorArrayFactory warriorFactory = new WarriorArrayFactory();
        warrior = warriorFactory.CreateArrayWarrior(squad, namesquad);
    }

    public boolean hasAliveWarriors() {
        for (Warrior war : warrior) {
            if (war.isAlive())
                return true;
        }
        return false;
    }

    @Override
    public String toString() {  //получить название отряда
        return name;
    }

    @Override
    public Squad clone() {  //получить копию отряда
        Squad sq = new Squad();
        int n = sq.warrior.size();
        sq.warrior = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sq.warrior.add(this.warrior.get(i).clone());
        }
        return sq;
    }
}
