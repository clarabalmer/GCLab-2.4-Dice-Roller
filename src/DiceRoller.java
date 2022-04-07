import java.util.Random;
import java.util.Scanner;
public class DiceRoller {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean repeatCasino = true;
		
		print("Welcome to the Grand Circus Casino!");
		
		while (repeatCasino) {
			print("\nWould you like to play craps, or roll a pair of N-sided dice?");
			System.out.print("type 'c' for craps, 'd' for N-sided dice, 'q' to quit: ");
			String response = scnr.next();
			
			if (response.equalsIgnoreCase("c")) {
				boolean repeatCraps = true;
				crapsRules();
				int counter = 0;
				int point = 0;
				while (repeatCraps) {
					counter++;
					int score = crapsRoll(counter);
					
					if (counter == 1) {
						switch (score) {
						case 2:
							print("Snake eyes! That's craps, you've lost!");
							repeatCraps = false;
							break;
						case 3:
							print("That's craps, you've lost! Sorry:/");
							repeatCraps = false;
							break;
						case 7:
						case 11:
							print("You won!");
							repeatCraps = false;
							break;
						case 12:
							print("Boxcars! That's craps, you've lost!");
							repeatCraps = false;
							break;
						default:
							print("You rolled a " + score + ", roll another before 7 to win!");
							point = score;
							enterContinue();
							break;
						}
					} else {
						if (score == point) {
							print("You beat the house! Too bad we weren't playing for money.");
							repeatCraps = false;
						} else if (score == 7) {
							print("That's a seven! Too bad, the house wins.");
							repeatCraps = false;
						} else {
							enterContinue();
						}
					}
				}
				
			} else if (response.equalsIgnoreCase("d")) {
				boolean repeatDice = true;
				print("\nGood choice!");
				System.out.print("How many sides should each die have? ");
				int numberOfSides = scnr.nextInt();
				int counter = 0;
				
				while (repeatDice) {
					counter++;
					print("Roll " + counter + ":");
					int a = generateRandomDieRoll(numberOfSides);
					int b = generateRandomDieRoll(numberOfSides);
					System.out.println(a);
					System.out.println(b);
					print(special(a,b));
					
					
					System.out.print("\nRoll again? (y/n) ");
					if (!scnr.next().equalsIgnoreCase("y")) {
						repeatDice = false;
					}
				}
				print("Play again soon!");
			} else if (response.equalsIgnoreCase("q")) {
				repeatCasino = false;
			} else {
				print("Oops, that wasn't one of the choices!");
			}
		}
		
		print("\nThanks for visiting!");
		
		
		
		
		
		scnr.close();
	}
	//roll two six sided dice, print results, return sum of the rolls
	public static int crapsRoll(int turn) {
		print("Roll " + turn + ":");
		int roll1 = sixSidedRandomDieRoll();
		int roll2 = sixSidedRandomDieRoll();
		System.out.println(roll1);
		System.out.println(roll2);
		return roll1 + roll2;
	}
	
	//print craps rules
	public static void crapsRules() {
		print("\nRules: win on a 7 or 11, lose on 2, 3, or 12.");
		print("Any other number becomes the point- roll the point again before 7 to win!");
		print("Press enter to roll or continue.");
		enterContinue();
	}
	//enter to continue shortcut
	public static void enterContinue() {
		try {
			System.in.read();
		} catch(Exception e) {}
	}
	//print shortcut
	public static void print(String toPrint) {
		System.out.println(toPrint);
	}
	//rolls an N sided die
	public static int generateRandomDieRoll(int sides) {
		Random rand = new Random();
		int randomRoll = 1 + Math.abs(rand.nextInt() % sides);
		
		return randomRoll;
	}
	//rolls a six sided die
	public static int sixSidedRandomDieRoll() {
		int randomRoll = 1 + (int)(Math.random() * ((6 - 1) + 1));
		return randomRoll;
	}
	// takes two rolls, returns special statement 
	public static String special(int roll1, int roll2) {
		if (roll1 == 1 && roll2 == 1) {
			return "Snake eyes! Too bad.";
		}
		if (roll1 + roll2 == 7 || roll1 + roll2 == 11) {
			return  Integer.sum(roll1, roll2) + " wins in craps!";
		}
		if (roll1 == 6 && roll2 == 6) {
			return "Boxcars! But also craps...";
		}
		if (roll1 + roll2 == 2 || roll1 + roll2 == 3 || roll1 + roll2 == 12 && roll1 != 6) {
			return "That's craps! If we were playing craps, that is...";
		}
		return "You rolled " + Integer.sum(roll1, roll2) + ".";
	}
	
}
