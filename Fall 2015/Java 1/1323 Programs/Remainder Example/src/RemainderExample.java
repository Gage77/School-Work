import java.util.Scanner;
public class RemainderExample 
{
	public static void main (String[] args)
	{
		// Read in two integers
		// Calculate the remainder
		// Show it to the user
		
		Scanner keyboard = new Scanner(System.in);
		
		int numOne;
		int numTwo;
		
		//Prompt for input
		System.out.println("Enter two integers");
		
		numOne = keyboard.nextInt();
		numTwo = keyboard.nextInt();
		
		//Calculate remainder
		int remainder = numOne % numTwo;
		
		//Show it to user
		System.out.println(numOne + " % " + numTwo + " = " + remainder);
		
		keyboard.close();
	}
}
