import java.util.Random;
import java.util.Scanner;

public class Project8_HunterBlack {
	
	public static int slotValue() {
		//This program will create a random number and return it to tokens
		Random rand = new Random();
		int randomNum = rand.nextInt(10) + 1;
		
		return randomNum;
	}
	
	public static int pointAssign(int token1, int token2, int token3, int totalPoints) {
		//Evaluates tokens and assigns points accordingly
		//Start by checking if you have tokens
		if (totalPoints > 0) {
			if (token1 == 7 && token2 == 7 && token3 == 7){
				totalPoints = totalPoints + 750;
				System.out.println("You got 750 points!");
			}
				else if (token1 == token2 && token2 == token3){
					totalPoints = totalPoints + 75;
					System.out.println("You got 75 points!");
				}
				else if ((token1 == 7 && token2 == 7)||(token2 == 7 && token3 == 7)||(token1 == 7 && token3 == 7)){
					totalPoints = totalPoints + 20;
					System.out.println("You got 20 points!");
				}
				else if (token1 == token2 || token2 == token3 || token1 == token3){
					totalPoints = totalPoints + 5;
					System.out.println("You earned 5 points!");
				}
			else {
				totalPoints = totalPoints - 1;
				System.out.println("You lost 1 point");
			}
		}
		else {
			System.out.println("You ran out of tokens. Thanks for playing!");
		}
		
		return totalPoints;
	}
	
	public static void slotMachine(Scanner input, int totalToken) throws InterruptedException {
		//This is the meat of the program. It asks user if they want to play
		String userInput; //sentinel
		System.out.println("You have " + totalToken + " tokens. Do you want to spin: Yes/No?");
		userInput = input.nextLine();
		
		while (userInput.equalsIgnoreCase("Yes")){
			
			//gives tokens random values
			int token1 = slotValue();
			int token2 = slotValue();
			int token3 = slotValue();
			
			//Prints out results
			System.out.println("Your spin was: " + token1 + " " + token2 + " " + token3);
			totalToken = pointAssign(token1, token2, token3, totalToken);
			System.out.println("You have " + totalToken + " tokens. Would you like to spin: Yes/No?");
			userInput = input.nextLine(); //sentinel
		}
		System.out.println("Thanks for playing!");
		
		
	}
	public static void main(String[]args) throws InterruptedException{
		Scanner input = new Scanner(System.in);
		int totalToken = 20;
		//goes to the bulk program passing a scanner and initial token value
		slotMachine(input, totalToken);
		
	}

}
