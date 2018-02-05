import java.util.ArrayList;

/**
 * This class creates Database objects containing
 * ArrayLists of type Movie, Series, and Episode
 */
public class Database 
{
	/** ArrayList containing Movie */
	private ArrayList<Movie> movies;
	/** ArrayList of Series */
	private ArrayList<Series> series;
	
	/**
	 * Constructor method for Database objects
	 * initiating the class variables movies, series, and episodes
	 */
	public Database()
	{
		movies = new ArrayList<Movie>();
		series = new ArrayList<Series>();
	}
	
	/**
	 * Inserts a Movie object into movies 
	 * 
	 * @param m		Movie object to insert
	 */
	public void insertMovie(Movie m)
	{
		movies.add(m);
	}
	
	/**
	 * Inserts a Series into series
	 * 
	 * @param s		Series object to insert
	 */
	public void insertSeries(Series s)
	{
		series.add(s);
	}
	
	/**
	 * Searches through specified ArrayList movies, series, or episodes
	 * and finds matching results, printing to the consoles
	 * 
	 * @param searchType		int indicating whether your searching through
	 * 							movies, series, or episodes
	 * @param param				The parameter to search by
	 */
	public static void searchForMovie(String title, String year, String sortType, String exactPartial, ArrayList<Movie> list)
	{
		//TODO check if exactPartial = "y". If so, they ARE looking for exact matches
	}
	
	public static void searchForSeries(String title, String year, String sortType, String exactPartial, String includeEpisodeTitle, String episdoeTitle, ArrayList<Series> list)
	{
		//TODO check if exactPartial = "y". if so, they ARE looking for exact matches
		//TODO check if includeEpisodeTitle = "y". if so, they DO want the episode title with match
	}
	
	
}
