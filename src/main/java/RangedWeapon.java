public class RangedWeapon extends Weapon{
    int ammo;

    public RangedWeapon(String name, int dmg, int ammo) {
        super(name, dmg);
        this.ammo = ammo;
    }

    public RangedWeapon(String pronoun, String name, int dmg, int ammo) {
        super(pronoun, name, dmg);
        this.ammo = ammo;
    }

    @Override
    public boolean canUse() {
        ammo -= 1;
        return (ammo >= 0);
    }

    @Override
    public String toString() {
        return super.toString() + " (ammo: " + ammo + ")";
    }
}
