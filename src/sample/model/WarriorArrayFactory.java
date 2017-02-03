package sample.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by lushi on 17.01.2017.
 */
public class WarriorArrayFactory {
    private String[] typeWarrior = {"Берсерк",
            "Лучник",
            "Викинг",
            "Защитник"};

    public ArrayList<Warrior> CreateArrayWarrior(ObservableList<String> squad, String namesquad) {
        ArrayList<Warrior> warrior = new ArrayList<>();
        for (int i = 0; i < squad.size(); i++) {
            switch (squad.get(i)) {
                case "Берсерк":
                    warrior.add(new Berserk());
                    break;
                case "Лучник":
                    warrior.add(new Archer());
                    break;
                case "Защитник":
                    warrior.add(new Defender());
                    break;
                case "Викинг":
                    warrior.add(new Viking());
                    break;
            }
            warrior.get(i).setSquadName(namesquad);
        }
        return warrior;
    }

    public String[] getTypeWarrior() {
        return typeWarrior;
    }
}
