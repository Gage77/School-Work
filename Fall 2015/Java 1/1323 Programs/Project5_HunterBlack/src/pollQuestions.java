import java.util.Scanner;
/** This program will calculate poll results based on user input during
 *  a speech. Output will print total # of times each number between
 *  1-5 is chosen, as well as positive, negative, and no changes. 
 * 
 * @author Hunter Black
 */

public class pollQuestions {
	public static void main(String[]args){
	Scanner scnr = new Scanner(System.in); 
	int userValue = 0; //sentinel control
	int sum1 = 0;  //running sums for each number chosen
	int sum2 = 0;
	int sum3 = 0;
	int sum4 = 0;
	int sum5 = 0;
	int previousValue = 3; //keep track of previous number, starting at 3
	
	int sumPositive = 0; //running sums for # of changes or no change
	int sumNegative = 0;
	int sumNoChange = 0;
	
	System.out.println("Enter the polling data or -1 to stop.");
	userValue = scnr.nextInt();
	
	while (userValue > 0) {
		
		//the following conditionals keep track of each # chosen
		if (userValue == 1){
			sum1 = sum1 + 1;
		}
		else if (userValue == 2) {
			sum2 = sum2 + 1;
		}
		else if (userValue == 3) {
			sum3 = sum3 + 1;
		}
		else if (userValue == 4) {
			sum4 = sum4 + 1;
		}
		else if (userValue == 5) {
			sum5 = sum5 + 1;
		}
		
		//the following conditionals keep track of # of changes pos, neg, or none
		if (previousValue < userValue) {
			sumPositive = sumPositive + 1;
		}
		else if (previousValue == userValue) {
			sumNoChange = sumNoChange + 1;
		}
		else {
			sumNegative = sumNegative + 1;
		}
		
		previousValue = userValue; //stores current value for comparison on next iteration 
		
		//get next user input value
		System.out.println("Enter the polling data or -1 to stop");
		userValue = scnr.nextInt();
	}	
	
	// output of poll results
	System.out.println("Positive changes: " + sumPositive);
	System.out.println("Negative changes: " + sumNegative);
	System.out.println("No change: " + sumNoChange);
	System.out.println("1 was chosen " + sum1 + " times.");
	System.out.println("2 was chosen " + sum2 + " times.");
	System.out.println("3 was chosen " + sum3 + " times.");
	System.out.println("4 was chosen " + sum4 + " times.");
	System.out.println("5 was chosen " + sum5 + " times.");
	
	scnr.close();
	
	}

}
