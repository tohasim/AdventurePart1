public class RangedWeapon extends Weapon{
    int ammo;

    public RangedWeapon(String name, int ammo) {
        super(name);
        this.ammo = ammo;
    }

    public RangedWeapon(String pronoun, String name, int ammo) {
        super(pronoun, name);
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
