import Enums.ReturnMessage;

public class Fighter {
    public int hp;
    public Weapon equippedWeapon;
    private boolean dead;

    public void attackSequence(Enemy enemyToAttack) {
        if (attackEnemy(enemyToAttack) == ReturnMessage.ENEMY_DEFEATED){
            //TODO: Ændr fjende til enemyToAttack.getName();
                        //TODO: Fjern system out herfra
            System.out.printf("%s besejret\n", enemyToAttack.getEnemyName());
        }else{
            enemyToAttack.attackEnemy(this);
            if (dead)
                System.out.println("player defeated");
            else
                System.out.println("Both live");
        }
    }


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
