public abstract class Weapon extends Item{

    public Weapon(String name) {
        super(name);
    }

    public Weapon(String pronoun, String name) {
        super(pronoun, name);
    }

    public abstract boolean canUse();

}
