import java.util.ArrayList;
import java.util.Collections;

public class Movie 
{
	
	private String title = "";
	private String year = "";
	private String form = "";
	private String multiple = "";
	
	/**
	 * Constructor for Movie Object
	 * 
	 * @param line line to be parsed by parseLine method
	 */
	public Movie(String line)
	{
		parseLine(line);
	}
	
	/**
	 * Receives line containing all movie info and
	 * parses it into appropriate variables, i.e.
	 * title, year, form, and multiple
	 * 
	 * @param line line from file to be parsed containing movie info
	 */
	public void parseLine(String line)
	{	
		String[] divided = line.split(" ");
		year = divided[divided.length - 1]; //Last item in divided ArrayList always the release year
		
		//step backwards in divided ArrayList starting after the release year 
		ArrayList<String> title0 = new ArrayList<String>(); //For holding title strings
		for (int i = divided.length - 2; i >= 0; --i)
		{
			//Check for form, i.e. TV or V by using .contains and .substring
			if (divided[i].contains("(TV)") || divided[i].contains("(V)"))
			{
				if (divided[i].contains("(TV)"))
					form = divided[i].substring(1,3);
				else
					form = divided[i].substring(1,2);
			}
			//Check for multiple (I, II, III, IV) using .contains and .substring
			else if (divided[i].contains("/I") || divided[i].contains("/II") || divided[i].contains("/III") || divided[i].contains("/IV"))
			{
				multiple = divided[i];
			}
			else
			{
				title0.add(divided[i]);
			}
		}
		
		//Because I was stepping backwards through "line", title0 is reverse of what i need
		//So i need to reverse it, then convert it to a string using a for/each loop
		Collections.reverse(title0);
		String title1 = "";
		for (String s : title0)
		{
			title1 += s + " ";
		}
		
		title = title1;
	}
	
	/**
	 * Gets movie title
	 * 
	 * @return Returns title of movie
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Gets movie release year
	 * 
	 * @return Returns release year of movie
	 */
	public String getYear()
	{
		return year;
	}
	
	/**
	 * Gets movie format (TV/V/none)
	 * 
	 * @return Returns TV, V, or null if no special format
	 */
	public String getForm()
	{
		return form;
	}
	
	/**
	 * Gets multiple number of movie if more than
	 * one movie with that title was released in that
	 * year
	 * 
	 * @return Returns int representation of Roman Numeral of movie
	 */
	public String getMultiple()
	{
		return multiple;
	}
	
	/**
	 * Overrides the toString method for this class and will return all info on
	 * movie in a string
	 * 
	 * @Override toString
	 * @return String returns the string representation of Movie information (title, mult, form, year)
	 */
	public String toString()
	{
		String movieInfo = title + " " + multiple + " " + form + "\t" + year;
		return movieInfo;
	}
	
}
