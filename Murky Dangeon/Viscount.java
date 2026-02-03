import java.util.Scanner;

public class Viscount extends Hero {
    public Viscount(){
        super("Viscount",500,70,100);
    }

    Scanner scan = new Scanner(System.in);
    @Override
    public void useSkill(Monster m, int choice) {
        switch (choice) {
            case 1: // Sword Attack
                int totaldamage = this.attack_point + this.bonusDamage;
                if (this.bonusDamageCredit > 0){
                    this.bonusDamageCredit--;
                }

                System.out.println("Viscount performed a sword attack! (" + totaldamage + " Damage)");
                if (bonusDamageCredit == 0) {
                    System.out.println(name + " The bonus power effect has gone");
                    bonusDamage = 0;
                }

                m.takeDamage(totaldamage);
                break;

            case 2: // Tax of Power
                if (this.energy >= 50) {
                    this.energy -= 50;
                    // Stealing monster's attack point
                    this.bonusDamage = m.get_attack_point(); 
                    this.bonusDamageCredit = 2; // lasts 2 tours
                    System.out.println("Viscount used the tax of power and stole the monster's attack point!");
                    System.out.println("He'll deal " + this.bonusDamage + " more damage for the next two rounds!");
                    System.out.println("Your move: ");
                    int choicen1 = scan.nextInt();
                    useSkill(m,choicen1);
                } else {
                    System.out.println("Insufficient energy! (needed: 50, you have: " + this.energy + ")");
                    System.out.println("Your move: ");
                    int choicen = scan.nextInt();
                    useSkill(m,choicen);
                }
                break;

            case 3: // All For One (ULTI)
                if (this.energy >= 100) {
                    this.energy -= 100;
                    System.out.println("Viscount: 'DUKE! CALL IN THE TROOPS!' ⚔️⚔️⚔️");
                    System.out.println("ARMY IS CHARGING! (200 Damage)");
                    m.takeDamage(200);
                } else {
                    System.out.println("Insufficient energy for the ultimate (needed: 100)");
                    System.out.println("Your move: ");
                    int choicen2 = scan.nextInt();
                    useSkill(m,choicen2);
                }
                break;
        }
    }

    @Override
    public void bringtheSkillsMenu() {
        System.out.println("\n--- VISCOUNT SKILLS ---");
        System.out.println("[1] Sword Attack  | Energy: 0   | damage: 70  | -> Standard sword attack.");
        System.out.println("[2] Tax of Power  | Energy: 50  | damage: 0   | -> Steals the monsters power for the next two rounds.");
        System.out.println("[3] All For One   | Energy: 100 | damage: 200 | -> (ULTI) Calls the whole army in!");
    }
}
