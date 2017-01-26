package sample; //разбить по пакетам

import java.util.Objects;

/**
 * Created by lushi on 21.11.2016.
 */
public class Archer implements Warrior { //почему не хотите вынести одно и то же в абстрактный класс?
    private String name, squad;
    private int health = 80;
    private int damage = 80;

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public String takeDamage(int damage) {
        health -= damage;
        return Integer.toString(health);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void setSquadName(String n) {
        squad = n;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + squad;
    }

    @Override
    public Archer clone() {
        Archer w = new Archer();
        w.name = this.name; //нет смысла клонировать строки - они неизменяемы
        return w;
    }

    @Override
    public void setName(String n) {
        name = n;
    }

    @Override
    public String getInfo() {
        return "HP: " + health + " DMG: " + damage;
    }
}
