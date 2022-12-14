public abstract class Weapon extends Item{
    int dmg;

    public Weapon(String name, int dmg) {
        super(name);
        this.dmg = dmg;
    }

    public Weapon(String pronoun, String name, int dmg) {
        super(pronoun, name);
        this.dmg = dmg;
    }

    public abstract boolean canUse();
    public abstract void use();

    public int getDmg() {
        return dmg;
    }

    public String getName() {
        return name;
    }
}
