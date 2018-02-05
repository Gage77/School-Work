import java.util.Scanner;
public class Fall2016 
{
		// These constants are used for a menu system
		private static final int ADD_DONOR = 1;
		private static final int DONATION = 2;
		private static final int SUM_DONATIONS = 3;
		private static final int SINGLE_DONOR_DETAILS = 4;
		private static final int QUIT = 5;
		
		public static void main(String[] args) 
		{
			Campaign candidate = new Campaign("Donald Duck");
			Scanner keyboard = new Scanner(System.in);
			
			//sentinel
			int menuChoice = 0;
			//Start of "menu" i.e. sentinel controlled loop
			while (menuChoice != QUIT)
			{
				menuChoice = menu(keyboard);
				
				if (menuChoice == ADD_DONOR) //1
					addDonor(keyboard, candidate);
				else if (menuChoice == DONATION) //2
					addDonation(keyboard, candidate);
				else if (menuChoice == SUM_DONATIONS) //3
					sumDonations(candidate);
				else if (menuChoice == SINGLE_DONOR_DETAILS) //4
					singleDonorDetails(keyboard, candidate);
				else if (menuChoice == QUIT) //5
					System.out.println("Goodbye");
				else //If number entered was not 1-5
					System.out.println("Unanticipated case");
			}
			
		}

		private static final int menu(Scanner keyboard)
		{
			System.out.println("Enter your choice below");
			System.out.println(ADD_DONOR + ": add new donor");
			System.out.println(DONATION + ": make donation");
			System.out.println(SUM_DONATIONS + ": find total donations");
			System.out.println(SINGLE_DONOR_DETAILS + ": single donor details");
			System.out.println(QUIT + ": quit");
			
			int value = keyboard.nextInt();
			keyboard.nextLine();
			if (value < ADD_DONOR || value > QUIT)
			{
				System.out.println(value + " is not in the legal range. Please re-enter");
				return menu(keyboard); // this is a cool trick called recursion
			}
			else // it was legal
			{
				return value;
			}
		}
		private static void addDonor(Scanner keyboard, Campaign candidate)
		{
			//Takes name of new donor, and passes it to the addDonor method in Campaign class
			System.out.println("Enter the name of the donor: ");
			String  name = keyboard.next();
			candidate.addDonor(name);
		}
		
		private static void addDonation(Scanner keyboard, Campaign candidate)
		{
			//Takes donor name and donation to add, and passes these to the addDonation method in Campaign class
			System.out.println("Enter the donor who is making the donation");
			String donorName = keyboard.nextLine();
			
			System.out.println("Enter the amount the donor wishes to donate");
			double donation = keyboard.nextDouble();
			
			candidate.addDonation(donorName, donation);
		}
		
		private static void sumDonations(Campaign candidate)
		{
			//Will print out total donations for specified Campaign
			System.out.println(candidate.getCandidateName() + " has $" + candidate.getAllDonations()
					+ " of donations");
		}
		
		private static void singleDonorDetails(Scanner keyboard, Campaign candidate)
		{
			System.out.println("Enter the name of the donor you wish to know about: ");
			String name = keyboard.next();
			
			//Get donations for this specific candidate 
			double money = candidate.getDonation(name);
			
			//Prints out details for specified donor
			System.out.println("" + name + " has donated $" + money);
		}
	}
