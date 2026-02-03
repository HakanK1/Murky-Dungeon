import java.util.Scanner;
public class Dracula extends Hero {
    public Dracula(){
        super("Dracula",500,75,100);
    }

    Scanner scand1 = new Scanner(System.in);

    @Override
    public void bringtheSkillsMenu() {
        System.out.println("\n--- DRACULA SKILLS ---");
        System.out.println("[1] Bite          | Mana: 0     | Damage: 75  | -> Bites and steals health by %20.");
        System.out.println("[2] Devour        | Mana: 70    | Effect: death | -> Devours the monster.");
        System.out.println("[3] Hide and wait | Mana: 0     | Effect: none  | -> He hides and takes no damage");
    }

    @Override
    public void useSkill(Monster m, int choice) {
        switch (choice) {
            case 1: // Bite
                System.out.println("Dracula has bit the monster! ");
                int damage = 75;
                m.takeDamage(damage);
                
                // Healing %20 percent of the dealed damage.
                int healing = (int)(damage * 0.20);
                int difference = this.maxhp - this.hp;
                this.hp += healing;
                if(this.hp > this.maxhp) this.hp = this.maxhp;
                if (difference < healing){
                    healing = difference;
                }

                System.out.println("Dracula has dealt "+ damage +" damage and refreshed "+ healing+" Health points");
                break;

            case 2: // Devour
                if (this.energy >= 70) {
                    this.energy -= 70;
                    System.out.println("Dracula devoured the monster!");
                    m.sethp(0); // Directly killing
                    System.out.println("Monster has been killed violently!");
                } else {
                    System.out.println("Not enough mana for the devour (Needed: 70, You have: " + this.energy + ")");
                    System.out.println("Your move: ");
                    int choicem2 = scand1.nextInt();
                    useSkill(m,choicem2);
                }
                break;
                
            case 3:
                System.out.println("Dracula waits in the darkness...");
                System.out.println("He waited enough! next move: ");
                int choicem3 = scand1.nextInt();
                useSkill(m,choicem3);
                break;
        }
    }
}
