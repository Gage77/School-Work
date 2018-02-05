import java.util.Arrays;
import java.util.Scanner;

public class Project10 {
	
	//Numbers for menu access
	public static final int ADD = 1;
	public static final int SEARCH = 2;
	public static final int QUIT = 3;
	
	public static void autoComplete(String[] list, Scanner input, int count)
	{
		// Search for emails letter by letter
		System.out.println("Please enter an email to search for, one letter at a time:");
		String userInput = input.next();
		String longestEmail = null;
		int length = 0;
		
		for (int k = 0; k < count - 1; ++k)
		{
			if (list[k].length() > length)
			{
				length = list[k].length();
				longestEmail = list[k];
			}
		}
		
		for (int i = 0; i < longestEmail.length(); ++i)
			// i will be the beginning index of the strings in the list after each outer iteration
			// so each string will get rid of the current first letter after each iteration
		{
			int ticker = 0;
			for (int j = 0; j < count; ++j)
			{
				if ((list[j].substring(i, list[j].length())).startsWith(userInput))
				{
					// print out emails with matching first letter
					ticker = ticker + 1;
					System.out.println(list[j]);
				}
				else if (!(list[j].substring(i, list[j].length())).startsWith(userInput))
				{
					System.out.println("Invalid letter");
					return;
				}
			}
			if (ticker == 1)
			{
				return;
			}
			// next letter
			userInput = input.next();
		}
		return;
	}
	
	public static void insertionSort(String[] list, String email, int count)
	{
		// Putting list[i] into the proper position in the array
		for (int i = 1; i < count; ++i)
		{
			// Move the value to the side
			String temp = list[i];
			int j = i;
			while (j > 0 && temp.compareTo(list[j - 1]) < 0)
			{
				// Shift values over if they are bigger than temp
				list[j] = list[j - 1];
				--j;
			}
			list[j] = temp;
		}
	}
	
	public static int insertEmail(String[] list, Scanner input, int count, int listSize)
	{
		System.out.println("Please enter valid email address: ");
		String emailArgument = input.next();
		boolean greenLight = false;
		
		//Check to see if the email entered is already in the array
		while (greenLight == false)
		{
			if (Arrays.binarySearch(list, 0, count, emailArgument) >= 0)
			{
				System.out.println("That email address already exists");
			}
			else if (Arrays.binarySearch(list, 0, count, emailArgument) < 0)
			{
				//increase the count of emails in the list by one
				count = count + 1;
				greenLight = true;
				break;
			}
			System.out.println("Please enter email address: ");
			emailArgument = input.next();
		}

		//If email does not already exist in array:
		//insert the email into the array in sorted order (insertion sort)
		if (greenLight)
		{
			//inserts email into the last position of elements before sorting
			list[count - 1] = emailArgument;
			insertionSort(list, emailArgument, count);
		}
		return count;
	}
	
	public static void menu(int SIZE_LIST) 
	{
		String[] list = new String[SIZE_LIST];
		Scanner input = new Scanner(System.in);
		int count = 0;
		
		//Ask menu questions
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Enter a new email.");
		System.out.println("2. Find existing email.");
		System.out.println("3. Quit");
		
		int chosenOption = input.nextInt();
		
		while (chosenOption != QUIT) 
		{
			if (chosenOption == ADD)
			{
				//go to insert method
				count = insertEmail(list, input, count, SIZE_LIST);
				chosenOption = 0;
			}
			else if (chosenOption == SEARCH)
			{
				//go to auto complete method
				autoComplete(list, input, count);
				chosenOption = 0;
			}
			System.out.println("Please choose one of the following options:");
			System.out.println("1. Enter a new email.");
			System.out.println("2. Find existing email.");
			System.out.println("3. Quit");
			
			chosenOption = input.nextInt();
		}
		return;
	}

	public static void main(String[]args) 
	{
		final int LIST_SIZE = 100;
		menu(LIST_SIZE);
		
		System.out.println("Thank you");
	}
	
}
