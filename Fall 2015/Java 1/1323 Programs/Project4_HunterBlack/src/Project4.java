import java.util.Scanner;

public class Project4 {
	public static void main(String[]args){
		
		Scanner scnr = new Scanner(System.in);
		
		int sum = 0; //running total of yes
		String userInput; //either yes or no
		
		System.out.println("Answer yes or no to the following statements:");
		System.out.println("I drink coffee every day.");
		userInput = scnr.nextLine();
		
		//beginning of quiz questions and running sum
		if (userInput.equalsIgnoreCase("yes")){
			sum = sum + 1;  
		}
		
		System.out.println("I know of 3 different coffee shops near me.");
		userInput = scnr.nextLine();
		
		if (userInput.equalsIgnoreCase("yes")){
			sum = sum + 1;
		}
		
		System.out.println("I can describe the taste of coffee using 5 different characteristics");
		userInput = scnr.nextLine();
		
		if (userInput.equalsIgnoreCase("yes")){
			sum = sum + 1;
		}
		
		//beginning of answers
		if (sum == 3){
			System.out.println("You are probably overly caffeinated right now");
		}
		else if (sum == 2){
			System.out.println("You are more than the average Starbucks go-er");
		}
		else if (sum == 1){
			System.out.println("Get out there and try some new coffee!");
		}
		else {
			System.out.println("You are either tired, or a tea drinker");
			}
		scnr.close();
		
	}

}
