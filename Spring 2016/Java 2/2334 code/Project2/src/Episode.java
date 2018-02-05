/**
 * This class creates Episode objects from a file
 * and allows the user to get information on the
 * Episode i.e. title, episode number, whether it
 * was suspended, and the year it aired.
 */
public class Episode 
{
	/** The episode's title */
	private String episodeTitle;
	/** The episodes number */
	private String episodeNumber;
	/** Whether the episode was suspended */
	private boolean suspended;
	/** The year the episode was aired */
	private String yearAired;
	
	/**
	 * Constructor method for Episode, taking in
	 * a single String containing all of the Episode's
	 * information and passing it to a parser method
	 * 
	 * @param line		String containing Episode's information
	 */
	public Episode(String line)
	{
		
	}
	
	/**
	 * Parser method that takes in a single String, and 
	 * divides it into the proper class variables
	 * i.e. episodeTitle, episodeNumber, suspended, and yearAired
	 * 
	 * @param line		String containing all Series information
	 */
	private void parseLine(String line)
	{
		
	}
	
	/**
	 * Returns the title of the Episode
	 * 
	 * @return		title of the Episode (String)
	 */
	public String getEpisodeTitle()
	{
		return null;
	}
	
	/**
	 * Returns the number of the Episode
	 * 
	 * @return		number of the Episode (String)
	 */
	public String getEpisodeNumber()
	{
		return null;
	}
	
	/**
	 * Returns whether the Episode was suspended or not
	 * 
	 * @return		True if the Episode was suspended, false if not (boolean)
	 */
	public boolean getSuspended()
	{
		
	}
	
	/**
	 * Returns the year the Episode aired
	 * 
	 * @return		The year the Episode aired (String)
	 */
	public String getYearAired()
	{
		return null;
	}
	
	/**
	 * Creates a String object containing all Episode information
	 * in a comprehensible form
	 * 
	 * @return		String representation of Episode information
	 * @override 	toString
	 */
	public String toString()
	{
		return null;
	}
}
