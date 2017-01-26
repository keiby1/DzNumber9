package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by lushi on 17.01.2017.
 */
public class WarriorArrayFactory {
    public ArrayList<Warrior> CreateArrayWarrior(ObservableList<String> squad, String namesquad) {
        ArrayList<Warrior> warrior = new ArrayList<>();
        for (int i = 0; i < squad.size(); i++) {
            if (squad.get(i).equals("Берсерк")) //switch. при изменении классов бойцов придется менять и это. №5
                warrior.add(new Berserk());
            else if (squad.get(i).equals("Лучник"))
                warrior.add(new Archer());
            else if (squad.get(i).equals("Защитник"))
                warrior.add(new Defender());
            else if (squad.get(i).equals("Викинг"))
                warrior.add(new Viking());
            warrior.get(i).setSquadName(namesquad);
        }
        return warrior;
    }
}
