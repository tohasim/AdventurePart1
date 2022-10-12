public class Enemy extends Fighter{
private String enemyName;

    //todo: lav private string navn, og tilføj til konstruktor samt lav getter
    public Enemy(String name, int hp, Weapon weapon) {
        super(name, hp);
        this.equippedWeapon = weapon;
    }
}


