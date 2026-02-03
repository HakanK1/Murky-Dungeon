import java.util.Random;
import java.util.Scanner;

public class Game{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        // You've got 3 credits
        int credit = 3;

        System.out.println("******************************************");
        System.out.println("    WELCOME TO THE MURKY DUNGEON ~BY HAKAN KOSAPINAR   "  );
        System.out.println("    You have three hero right. Good luck!");
        System.out.println("******************************************");

        // 3 credit for loop
        for (int i = 1; i <= credit; i++) {
            
            Hero activeHero = null;
            boolean heroChosen = false;

            while (!heroChosen) {
                System.out.println("\n========================================");
                System.out.println(i + ". Choose your hero (credits left: " + (credit - i + 1) + ")");
                System.out.println("========================================");
                System.out.println("[1] VISCOUNT   : Swordsman, Summons an army.");
                System.out.println("[2] TERMINATOR : advances with XP, Roasts the enemies with laser gun.");
                System.out.println("[3] DRACULA    : Steals health and has a one shot and kill skill.");
                System.out.println("[0] Exit Game");
                System.out.print("Your choice: ");
                
                int herochoice = scanner.nextInt();
                
                if (herochoice == 0){
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                }

                // Polymorphism.
                switch (herochoice) {
                    case 1:
                        activeHero = new Viscount(); 
                        break;
                    case 2:
                        activeHero = new Terminator();
                        break;
                    case 3:
                        activeHero = new Dracula();
                        break;
                    default:
                        System.out.println("Wrong choice! Please select again.");
                        continue;
                }

                System.out.println("\nChosen Hero: " + activeHero.name);
                System.out.println("Are you sure? ([1] for Yes / [2] for No): ");
                int confirmChoice = scanner.nextInt();
                
                if (confirmChoice == 1) {
                    heroChosen = true;
                } else if (confirmChoice == 0) {
                    System.exit(0);
                } else{
                    System.out.println("Selection cancelled. Choose your hero again.");
                }

            }

            System.out.println("\n>>> " + activeHero.name + " has entered the area! <<<");

            // Cover some distance randomly
            Random randx = new Random();
            int covered_distancex = randx.nextInt(100);
            activeHero.cover_distance(covered_distancex);
            System.out.println("Hero moved " + covered_distancex + " meters forward.");

            // --- WAR LOOP STARTS CHARGE!!! ---
            int attack_incrementx = 0, hp_incrementx = 0;
            Monster monster = new Monster(0,0); 
            int health_backup = monster.getHp();
            System.out.println("Encountered a monster! (Health: " + monster.getHp() + ")");
            // Battle loop
            while (activeHero.isAlive() && monster.isAlive()) {
                // 1. New Round
                System.out.println("\n--- Round ---");
                System.out.println("You: " + activeHero.hp + " HP | Monster: " + monster.getHp() + " HP");
                activeHero.UpdateInStart();

                // Skills menu
                activeHero.bringtheSkillsMenu();
                System.out.print("Your move: ");
                int skillChoice = scanner.nextInt();
                // 3. Attack
                activeHero.useSkill(monster, skillChoice);
                

                // 4. Monster retaliates if it's still alive
                if (monster.isAlive()) {
                    int damagex = monster.attack();
                    System.out.println("Monster has made a retaliation! (-" + damagex + " HP)");
                    activeHero.takeDamage(damagex);
                } else {
                    System.out.println("\n*** YOU'VE BEATEN THE MONSTER ***");
                    activeHero.gainExperience(50); // XP AWARD
                    int healedHp = (health_backup / 10);
                    activeHero.heal(healedHp);
                    if (activeHero.getHp() > activeHero.maxhp) {
                        activeHero.setHp(activeHero.maxhp);
                        System.out.println("Your hp is already full! could not be healed.");
                    } else {
                        System.out.println("Your hero healed " + healedHp +" Health points.");
                    }
                    
                    attack_incrementx += 10;
                    hp_incrementx += 10;
                    monster = new Monster(hp_incrementx,attack_incrementx);
                    health_backup = monster.getHp();
                    covered_distancex = randx.nextInt(100);
                    activeHero.cover_distance(covered_distancex);
                    System.out.println("\nHero moved " + covered_distancex + " meters forward.");
                    System.out.println("Total distance: "+ activeHero.covered_distance);
                    System.out.println("Encountered a monster! (Health: " + monster.getHp() + ")");
                }
            }
            
            // if the loop ends,that means it's because either the hero or the monster dies.
            if (!activeHero.isAlive()) {
                System.out.println("\nUnfortunately " + activeHero.name + " died in the battle");
                System.out.println("Final distance: "+ activeHero.covered_distance);
                System.out.println("Total experience: "+ activeHero.getExp());
                attack_incrementx = 0;
                hp_incrementx = 0;
            
            System.out.println("----------------------------------------");
            if (i < credit) {
                System.out.println("Next hero is preparing now...");
            } else {
                System.out.println("No credits left, GAME OVER.");
            }
        }
    }
}
}