import Enums.ReturnMessage;

public class Fighter {
    public int hp;
    public Weapon equippedWeapon;
    private boolean dead;




    public ReturnMessage attackEnemy(Fighter enemy){
        int dmg = equippedWeapon.getDmg();
        enemy.damage(dmg);
        if (enemy.isDead()){
            return ReturnMessage.ENEMY_DEFEATED;
        }
        return ReturnMessage.OK;
    }

    public boolean isDead() {
        return dead;
    }

    public int damage(int dmg) {
        if (hp - dmg <= 0)
            dead = true;
        hp -= dmg;
        return hp;
    }
}
