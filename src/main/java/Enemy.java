public class Enemy {
    private static int hp = 300;
    private boolean dead;
    //TODO: Giv enemy et equiped weapon + hp
    public int damage(int dmg) {
        if (hp - dmg <= 0)
            dead = true;
        hp -= dmg;
        return hp;
        //TODO: Implementer enemy damage og returner hvor meget enemy dealer
    }
    public boolean isDead(){
        return dead;
    }
}
