public class Fighter {
    private int hp;
    public Weapon equippedWeapon;
    private String name;
    private boolean dead;
    private static int HP_MAX = 100;
    public Fighter(String name, int hp){
        this.name = name;
        this.hp = hp;
    }


    public int attackEnemy(Fighter enemy){
        equippedWeapon.use();
        int dmg = equippedWeapon.getDmg();
        enemy.damage(dmg);
        return dmg;
    }

    public void heal(int healAmount){
        if (getHp() + healAmount < HP_MAX )
            hp = getHp() + healAmount;
        else hp = HP_MAX;
    }
    public boolean isDead() {
        return dead;
    }


    public int damage(int dmg) {
        if (getHp() - dmg <= 0)
            dead = true;
        hp = getHp() - dmg;
        return getHp();
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }
}
