package sample.model;

/**
 * Created by lushi on 12.12.2016.
 */
public abstract class Warrior {
    protected int health, damage;
    protected String squadName, name;

    public Warrior(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public int attack() {
        return damage;
    }

    public String takeDamage(int damage) {
        health -= damage;
        return Integer.toString(health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setSquadName(String name) {
        this.squadName = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquadName() {
        return squadName;
    }

    public String getInfo() {
        return "HP: " + health + " DMG: " + damage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + squadName;
    }

    @Override
    protected Warrior clone() throws CloneNotSupportedException {
        return (Warrior) super.clone();
    }
}