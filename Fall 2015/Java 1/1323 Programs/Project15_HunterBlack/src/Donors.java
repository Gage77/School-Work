import java.util.ArrayList;

public class Donors
{
	private String name = "";
	private ArrayList<Double> donations = new ArrayList<Double>();
	
	//creates new Donors object with no donations
	public Donors(String name)
	{
		this.name = name;
	}
	//Creates new Donors object with an initial donation
	public Donors(String name, double donation)
	{
		this.name = name;
		donations.add(donation);
	}
	
	//Returns the name of the donor
	public String getName()
	{
		return name;
	}
	//Returns total amount the donor has donated
	public double getTotalDonations()
	{
		double total = 0.0;
		for (int i = 0; i < donations.size(); ++i)
		{
			total = total + donations.get(i);
		}
		
		return total;
	}
	//Returns string object representing all of Donor object's information
	public String toString()
	{
		String str = "";
		str = "Donor's name: " + this.name + ", Donor amount: " + this.getTotalDonations();
		return str;
	}
	//Add a donation to this Donors object's list of donations
	public void addDonation(double donation)
	{
		donations.add(donation);
	}
}
