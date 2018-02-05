import java.util.Scanner;

/** This program will ask user quiz questions
 * and return an answer based on the accumulative sum
 * of their responses.
 * @author Hunter Black
 */

public class Project6 {
	
	public static int askQuizQuestion(String prompt, Scanner input) {
		//This asks question and receives the user input, storing the value 
		//and passing it back to the main method
		String userInput; //yes or no
		int sum = 0; //accumulating values
		System.out.println(prompt); //question asked
		System.out.println("Enter yes or no:");
		
		userInput = input.nextLine();
		
		if (userInput.equalsIgnoreCase("Yes")){
			sum = 1;
		}
		else if (userInput.equalsIgnoreCase("No")){
			sum = 0;
		}
		
		return sum;
		
	}
	
	public static void printSurveyResults(int sum) {
		//This receives the user input from askQuizQuestion method
		//and evaluates it, outputing a specific answer
		String result;
		if (sum >= 0 && sum <= 3){
			result = "More exhausted than stressed out";
		}
		else if (sum >= 4 && sum <= 6) {
			result = "Beginning to stress out.";
		}
		else if (sum >= 7 && sum <= 9) {
			result = "Possibly stressed out";
		}
		else {
			result = "Probably stressed out";
		}
		
		System.out.println(sum + ": " + result);
		
		return;
	}
	
	public static void main(String[]args){
		int sum = 0; //accumulating sum
		Scanner input = new Scanner(System.in);
		
		//next 12 are quiz questions w/ call for askQuizQuestion method
		sum = askQuizQuestion("\"I find myself less eager to go back "
				+ "to work or to resume my chores after a weekend.\"", input);
		sum = sum + askQuizQuestion("I feel less and less patient "
				+ "and/or sympathetic listening to other people’s problems.", input);
		sum = sum + askQuizQuestion("I ask more “closed-ended questions to "
				+ "discourage dialogue with friends and co-workers "
				+ "than “open-ended” ones to encourage it.", input);
		sum = sum + askQuizQuestion("I try to get away from people as soon as I can.", input);
		sum = sum + askQuizQuestion("My dedication to work, exercise, diet, "
				+ "and friendship is waning.", input);
		sum = sum + askQuizQuestion("I am falling further behind in many "
				+ "of the responsibilities in my life.", input);
		sum = sum + askQuizQuestion("I am losing my sense of humor.", input);
		sum = sum + askQuizQuestion("I find it more and more difficult to see people socially.", input);
		sum = sum + askQuizQuestion("I feel tired most of the time.", input);
		sum = sum + askQuizQuestion("I don't seem to have much fun anymore.", input);
		sum = sum + askQuizQuestion("I feel trapped.", input);
		sum = sum + askQuizQuestion("I know what will make me feel better, but "
				+ "I just can’t push myself to do it and I’ll “Yes, but” any "
				+ "suggestions that people make.", input);
				
		//Print out results from quiz
		printSurveyResults(sum);
		
		return;
	}
}

