import java.util.ArrayList;
import java.util.Collections;

/**
 * This class creates Movie objects from a file 
 * and allows the user to get various information
 * about that Movie
 */
public class Movie 
{
	/** Title of Movie */
	private String title;
	/** Year that the movie was released*/
	private String year;
	/** Format the Movie was released in (TV/V) */
	private String form;
	/** If two Movies with the same title were released in the same
	 * year, they will be a multiple
	 */
	private String multiple;
	
	/**
	 * Constructor method for Movie objects
	 * takes in a String containing all information of 
	 * a movie, and sends it to a parser method
	 * 
	 * @param line		String containing all Movie information
	 */
	public Movie(String line)
	{
		parseLine(line);		
	}
	
	/**
	 * Parser method that takes in Movie information
	 * and parses it to its corrective class variable,
	 * i.e. title, year, form, multiple
	 * 
	 * @param line		String containing all Movie information
	 */
	private void parseLine(String line)
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
	 * Returns the title of the Movie
	 * 
	 * @return		The title of the Movie (String)
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Returns the release year of the Movie
	 * 
	 * @return		The year of the Movie (String)
	 */
	public String getYear()
	{
		return year;
	}
	
	/**
	 * Returns the format that the Movie was released in (TV/V)
	 * 
	 * @return		The release format of the Movie (String)
	 */
	public String getForm()
	{
		return form;
	}
	
	/**
	 * Returns the multiple of the Movie if a Movie with the
	 * same title was released in the same year
	 * 
	 * @return		The multiple number of the Movie, if applicable (String)
	 * 				Null if not a multiple
	 */
	public String getMult()
	{
		return multiple;
	}
	
	
	
	/**
	 * Converts the Movie's information into a comprehensible String
	 * 
	 * @return		The String representation of Movie information
	 * @override	toString
	 */
	public String toString()
	{
		String movieInfo = title + " " + multiple + " " + form + "\t" + year;
		return movieInfo;
	}
}
