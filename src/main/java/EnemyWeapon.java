public class EnemyWeapon extends Item {
    int dmg;

    public EnemyWeapon(String name, int dmg) {
        super(name);
        this.dmg = dmg;
    }

    public int getDmg() {
        return dmg;
    }
}
