import java.util.Random;

public class Monster {
    private int hp;
    private int attack_point;

    // I have added difficulty scaling here, that is, as the hero gains experience,
    // the monsters will be stronger in terms of health and attack power.
    // but when that hero dies, the monster stats will reset to the base values.
    // and that process will be repeated until the hero dies again.
    
    public Monster(int hp_increment,int attack_increment){
        Random rand = new Random();
        this.hp = rand.nextInt(131+hp_increment)+70;
        this.attack_point = rand.nextInt(51+attack_increment) +10;
    }

    public int attack(){
        return this.attack_point;
    }

    public void takeDamage(int Damaged){
        this.hp -= Damaged;
    }

    public boolean isAlive(){
        return this.hp > 0;
    }

    public int getHp(){return this.hp;}
    public void sethp(int hp){this.hp = hp;}
    public int get_attack_point(){return this.attack_point;} // For the viscount.
    
}
