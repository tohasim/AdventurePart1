public class Enemy extends Fighter{
private String enemyName;

    //todo: lav private string navn, og tilføj til konstruktor samt lav getter
    public Enemy(Weapon equippedWeapon, String enemyName, int hp) {
        this.equippedWeapon = equippedWeapon;
        this.enemyName = enemyName;
        this.hp = hp;
    }public String getEnemyName() {
        return enemyName;
    }

    public int getHp(){
        return hp;
    }


    }

