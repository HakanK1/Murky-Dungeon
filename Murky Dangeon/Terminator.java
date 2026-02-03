import java.util.Scanner;

public class Terminator extends Hero{
    public int shield_duration = 0;
    public Terminator(){
        super("Terminator",600,90,100);
    }

    Scanner scannerx = new Scanner(System.in);

    @Override
    public void UpdateInStart() {
        super.UpdateInStart();

        int xpBonus = this.exp / 25; // Each 25 xp gives +1 damage
        int current_damage = 90 + xpBonus;

        if (this.shield_duration >= 0){
            System.out.println("Shields: "+ this.shield_duration);
        }

        System.out.println("[System]: Augment is active. Current attack_point: "+current_damage);
    }

    @Override
    public void takeDamage(int damage){
        if (this.shield_duration > 0){
            System.out.println("Immune shield is active %80 of the damage has been absorbed. ");
            damage = (int)(damage * 0.20);
            this.shield_duration--;
            if (this.shield_duration == 0)
                System.out.println("Terminator's shield has been deactivated.");
        }
        System.out.println("Taken damage: "+ damage);
        super.takeDamage(damage);
    }

    @Override
    public void bringtheSkillsMenu() {
        int current_damage = 90 + (this.exp / 25);

        System.out.println("\n--- TERMINATOR SYSTEMS ---");
        System.out.println("[1] Laser Attack  | Energy: 0   | Damage: " + current_damage + " | -> Laser attack (advances with XP)");
        System.out.println("[2] Augment       | Passive      | Effect: Power+ | -> Current XP: " + this.exp + " (runs automatically)");
        System.out.println("[3] Immune        | Energy: 60  | Shield     | -> decreases the taken damage by %80 for two rounds.");
    }

    @Override
    public void useSkill(Monster m, int choice) {
        // Passive attack increment is being taken into account.
        int current_damage = 90 + (this.exp / 25); 

        switch (choice) {
            case 1: // Laser Attack
                System.out.println("Terminator has performed a laser attack (" + current_damage + " damage)");
                m.takeDamage(current_damage);
                this.isPassive = false;
                break;

            case 2: // Augment (Passive)
                System.out.println("--- AUGMENT (PASSIVE) ---");
                System.out.println("Currrent XP: " + this.exp);
                System.out.println("This skill's automatic, as terminator gains experience, it becomes more powerful");
                this.bringtheSkillsMenu();
                System.out.print("Your move: ");
                int skillChoice = scannerx.nextInt();
                useSkill(m, skillChoice);
                break;

            case 3: // Immune
                if (this.energy >= 60) {
                    this.energy -= 50;
                    this.shield_duration += 2;
                    System.out.println("Immune mode has been activated!");
                    System.out.println("Taken damage will be decreased by %80 in the following 2 rounds");
                    System.out.println("Choose your next move: ");
                    int skillChoice2 = scannerx.nextInt();
                    useSkill(m, skillChoice2);

                } else {
                    System.out.println("Not enough energy");
                    System.out.println("Your move: ");
                    int skillChoice3 = scannerx.nextInt();
                    useSkill(m, skillChoice3);
                }
                break;
        }
    }
}
