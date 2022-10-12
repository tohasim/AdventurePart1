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
        return (ammo > 0);
    }

    @Override
    public void use() {
        ammo--;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (ammo: %d, dmg: %d)", ammo, dmg);
    }
}
