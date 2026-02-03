public class Hero {
    protected String name;
    protected int hp;
    public int maxhp;
    protected int exp;
    public int covered_distance;
    protected int attack_point;
    protected int energy;
    protected int max_energy;
    public boolean isPassive = false;

    protected int bonusDamage = 0;
    protected int bonusDamageCredit = 0;

    public Hero(String name,int hp,int attack_point,int energy){
        this.name = name;
        this.hp = hp;
        this.maxhp = hp;
        this.attack_point = attack_point;
        this.energy = energy;
        this.max_energy = energy;
        this.exp = 0;
        this.covered_distance = 0;
    }

    public void UpdateInStart() {
        if (bonusDamageCredit >= 0) {
            System.out.println("Extra damage credit for "+this.getName() + ": "+this.bonusDamageCredit);
        }
        if (energy < max_energy) energy += 15;
        System.out.println("Current energy: "+ this.energy +" Max energy: " + this.max_energy);
    }

    public boolean isAlive(){
        return (this.hp > 0); 
    }

    public void takeDamage(int damage){
        this.hp = (this.hp - damage);
        if (this.hp < 0) 
            this.hp = 0;
    }

    public void heal(int amount){
        this.hp += amount;
    }

    public void gainExperience(int amount){
        this.exp += amount;
    }

    public void cover_distance(int distance){
        this.covered_distance += distance;
    }

    public void bringtheSkillsMenu() {
        System.out.println("This heroes skills are yet not described.");
    }

    public void useSkill(Monster m, int choice){ }

    public int getHp(){return this.hp;}
    public String getName(){return this.name;}
    public int getExp(){return this.exp;}
    public void setHp(int newhp) {this.hp = newhp;}
}
