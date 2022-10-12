public class MeleeWeapon extends Weapon{
    public MeleeWeapon(String name, int dmg) {
        super(name, dmg);
    }

    public MeleeWeapon(String pronoun, String name, int dmg) {
        super(pronoun, name, dmg);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {

    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (dmg: %d)", dmg);
    }
}
