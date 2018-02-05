import java.util.ArrayList;

public class Campaign 
{

	private String candidateName;
	private ArrayList<Donors> donors = new ArrayList<Donors>();
	
	public Campaign(String  name)
	{
		//Create Campaign object with candidateName "name"
		candidateName = name;
	}
	
	public String getCandidateName()
	{
		//Create Campaign object with no initial candidateName
		return candidateName;
	}

	public String getDonors()
	{
		//Returns String representation of candidateName's donors
		String result = candidateName + "\n";
		result += donors.toString();
		
		return result;
	}
	public double getAllDonations()
	{
		//Initial sum that will be increased for each donor's donations
		double sum = 0.0;
		//Step through donors to get each Donors donations, then add to sum
		for (int i=0; i<donors.size(); ++i)
		{
			Donors d = donors.get(i);
			sum += d.getTotalDonations();
		}
		return sum;
	}
	
	public void addDonor(String name)
	{
		//Step through the donors ArrayList to check for existing donors
		for (int i = 0; i < donors.size(); ++i)
		{
			String str = (donors.get(i).getName());
			//If the test String str already exists in donors
			if (str.equals(name))
			{
				System.out.println("That donor already exists");
				return;
			}
		}
		//If this donor does not already exist, add new donor to donors ArrayList
		Donors n = new Donors(name);
		donors.add(n);
		
		
		return;
	}
	
	public double getDonation(String donor)
	{
		//Run through donations to find the donor with name "donor", and get their donations
		double total = 0.0;
		for (int i = 0; i < donors.size(); ++i)
		{
			Donors d = donors.get(i);
			//Once Donors with name donor is found, get their total donations and store in total
			if (d.getName().equals(donor))
			{
				total = d.getTotalDonations();
			}
		}
		
		return total;
	}
	
	public String getDonationList(String donor)
	{
		//Run through donations to get list of donors names
		for (int i=0; i<donors.size(); ++i)
		{
			Donors d = donors.get(i);
			if (d.getName().equals(donor))
			{
				return d.toString();
			}
		}
		
		//If that donor already exists
		return "No donor with that name was found";
	}
	
	public void addDonation(String donorName, double donation)
	{
		//Step through donors ArrayList and find donorName, then add "donation" to that donorName
		for (int i = 0; i < donors.size(); ++i)
		{
			Donors d = donors.get(i);
			//Once Donors with name donorName is found, add "donation" to their donations
			if (d.getName().equals(donorName))
			{
				d.addDonation(donation);
			}
		}
	}
}

