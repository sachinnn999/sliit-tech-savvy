package model;

public class SuperDot extends Dot {
    String weapon;

    public SuperDot() {
        super();
    }

    public SuperDot(String weapon, int x, int y) {
        super(x, y);
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "SuperDot{" +
                "weapon='" + weapon + '\'' +
                '}';
    }
}
