public class MeleeWeapon extends Weapon{
    public MeleeWeapon(String name) {
        super(name);
    }

    public MeleeWeapon(String pronoun, String name) {
        super(pronoun, name);
    }

    @Override
    public boolean canUse() {
        return true;
    }
}
