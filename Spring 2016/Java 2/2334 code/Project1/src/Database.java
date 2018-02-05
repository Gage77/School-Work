import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database 
{

	private static ArrayList<Movie> list;
	
	/**
	 * Constructor creating Database Object consisting of an ArrayList holding type Movie
	 */
	public Database()
	{
		list = new ArrayList<Movie>();
	}
	
	/**
	 * Adds Movie Object to Database's array list
	 * 
	 * @param movie Movie Object to be added to list
	 */
	public void insertMovie(Movie movie)
	{
		list.add(movie);
	}
	
	/**
	 * Searches through Database Object using specified parameter
	 * until movie(s) matching description are found, 
	 * then prints that info to the console
	 * 
	 * @param param Search parameter (title, year, TV/V)
	 * @param movies list of movies containing the parameter
	 * @param searchType number will indicate type of parameter (title = 1, year = 2, form = 3)
	 */
	public void printInfo(String param, ArrayList<String> movies, int searchType)
	{
		movies.removeAll(movies);
		for (int i = 0; i < list.size(); ++i)
		{
			if (searchType == 1)
			{
				if (list.get(i).getTitle().contains(param))
					movies.add(list.get(i).toString() + "\n");
			}
			else if (searchType == 2)
			{
				if (list.get(i).getYear().contains(param))
					movies.add(list.get(i).toString() + "\n");
			}
			else if (searchType == 3)
			{
				if (list.get(i).getForm().contains(param))
					movies.add(list.get(i).toString() + "\n");
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid parameter to search for");
		}
	}	
	
}
