import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AutoComplete_ArrayList 
{
	
	//Menu "buttons"
	public static final int ADD = 1;
	public static final int SEARCH = 2;
	public static final int QUIT = 3;
	
	public static void main(String[]args)
	{
		menu();
		
		//if QUIT is chosen
		System.out.println("Thank you");
	}
	
	public static void menu()
	{
		Scanner input = new Scanner(System.in);
		ArrayList<String> emailList = new ArrayList<String>(1);
		
		//ask user what they want to do
		System.out.println("Choose from the following options:");
		System.out.println("1: Add an email");
		System.out.println("2: Search for an existing email");
		System.out.println("3: Quit");
		
		//sentinal
		int value = input.nextInt();
		
		//menu loop
		while (value != QUIT)
		{
			if (value == ADD)
			{
				add(emailList, input);
				System.out.println(emailList);
				value = 0;
			}
			else if (value == SEARCH)
			{
				search(emailList, input);
				value = 0;
			}
			
			System.out.println("Choose from the following options:");
			System.out.println("1: Add an email");
			System.out.println("2: Search for an existing email");
			System.out.println("3: Quit");
			value = input.nextInt();
		}
	}
	
	public static void add(ArrayList<String> list, Scanner input)
	{
		System.out.println("Enter email to be inserted to database: ");
		String email = input.next();
		
		if (list.contains(email))
		{
			System.out.println("That email already exists");
			return;
		}
		
		else
		{
			list.add(email);
			Collections.sort(list);
		}
		return;
	}
	
	public static void search(ArrayList<String> list, Scanner input)
	{
		System.out.println("Enter the email you which to search for, one letter at a time: ");
		String value = input.next();
		//Used to find the value for the for loop for searching
		int length = 0;
		
		ArrayList<String> searchList = new ArrayList<String>();
		searchList = list;
		
		//Find the length for the for loop later on so it wont go past the length of the longest email
		for (int i = 0; i < list.size(); ++i)
		{
			String test = list.get(i);
			if (test.length() > length)
			{
				length = test.length();
			}
		}
		
		for (int i = 0; i < length; ++i)
			// i will be the beginning index of the strings in the list after each outer iteration
			// so each string will get rid of the current first letter after each iteration
		{
			int ticker = 0;
			for (int j = 0; j < list.size(); ++j)
			{
				if ((list.get(j).substring(i, list.get(j).length())).startsWith(value))
				{
					// print out emails with matching first letter
					ticker = ticker + 1;
					System.out.println(list.get(j));
				}
			}
			if (ticker == 1)
			{
				System.out.println("Email found");
				return;
			}
			// next letter
			value = input.next();
		}
		return;
	}

}
