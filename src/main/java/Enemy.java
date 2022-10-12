public class Enemy extends Fighter{

    //todo: lav private string navn, og tilføj til konstruktor samt lav getter
    public Enemy(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
        hp = 30;
    }
}
