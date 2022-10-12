public class Enemy extends Fighter{
private String enemyName;

    public Enemy(String name, int hp, Weapon weapon) {
        super(name, hp);
        this.equippedWeapon = weapon;
    }
}


