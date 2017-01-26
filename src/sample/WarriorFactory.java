package sample;

/**
 * Created by lushi on 17.01.2017.
 */
public class WarriorFactory {
    public Warrior CreateWarrior(String type) {
        Warrior warrior = null;
        switch (type) { //при изменении классов бойцов придется менять и это. №6. Это рекорд!
            case "Лучник":
                warrior = new Archer();
                break;
            case "Берсерк":
                warrior = new Berserk();
                break;
            case "Викинг":
                warrior = new Viking();
                break;
            case "Защитник":
                warrior = new Defender();
                break;
        }
        return warrior;
    }
}
