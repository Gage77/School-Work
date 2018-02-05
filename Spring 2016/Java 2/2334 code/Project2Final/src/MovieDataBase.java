import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MovieDataBase 
{
	private String mdbMovieYear;
	private String mdbSentToVideo;
	private String mdbMovieInfo;
	private String mdbMovieTitle;
	private ArrayList<Movie> movieList;
	
	
	
	/**
	 * General constructor for MovieDataBase object, which takes in a file and passes
	 * it to a seperate method to be parsed
	 * 
	 * @param fileName	-	File containing information on Movie
	 * @throws IOException
	 */
	public MovieDataBase(String fileName) throws IOException
	{
		parse(fileName);
	}
	
	/*
	 * This method will take in a file containing the string arguments needed to create the 
	 * movie objects. First it will read the file line by line, parsing the strings based on their values
	 * and finally store them into movie objects.
	 * 
	 * @param: 
	 * fileName - The name of the file being read.
	 * 
	 * @return:
	 * An ArrayList of type Movie which will house all of the movies being read from a file.
	 */
	public void parse(String fileName) throws IOException
	{
		// The empty string which will hold the line being read
		String nextLine;
				
		// The FileReader object which will deal with the file
		FileReader fileReader = new FileReader(fileName);
				
		// This Buffered reader object will read in the argument input
		BufferedReader br = new BufferedReader(fileReader);
				
		// Array of strings which will hold the parsed values of the line being read
		String[] list;
				
		movieList = new ArrayList<Movie>();
				
		// The first empty line will be read
		nextLine = br.readLine();
				
		// Loop will iterate throughout the entire file
		while(nextLine != null)
		{
			// Will split based on characteristics described
			list = nextLine.split("[ |\t]+");
					
			// First value of interest is year of movie
			// Since the program will read each line from right to left, the formatting of the lines
			// means that we are guaranteed the movie's year will be the rightmost spot
			mdbMovieYear    = list[list.length - 1];
					
			// Conditional which will determine whether there is television information within the line
			// Since we know that (year) has a character length of 6 we can conclude that anything less
			// then that length must be television information
			// As stated before, the method will read from right to left so it will assign the television
			// info and the movie info accordingly.
			if(list[list.length - 2].length() < 6)
			{
				mdbSentToVideo  = list[list.length - 2];
						
				mdbMovieInfo    = list[list.length - 3];
			}
					
			// If there is no television info, the only output will be that of the movie info
			else
			{
				mdbSentToVideo = " ";
				mdbMovieInfo   = list[list.length - 2];
			}
					
			// Movie title will be found regardless of the previous conditional statements. This first
			// line is to prevent the for loop from writing over the inputs of the title being read.
			mdbMovieTitle = "";
					
			// Since we know that everything remaining on the string line is the title, and we know that 
			// we are now 3 spaces from the rightmost spot in the line, reading the whole title is easy.
			// We create a for loop which will read from left all the way to the proscribed destination.
			for(int i = 0;i < list.length - 3; i++)
			{
				mdbMovieTitle += list[i] + " ";
			}
					
			// Movie object which will take in the parameters assigned from reading each line
			Movie movieObject = new Movie(mdbMovieTitle, mdbMovieInfo, mdbSentToVideo, mdbMovieYear);
					
			System.out.println(movieObject);
			// Each Movie object will be added to the ArrayList
			movieList.add(movieObject);
					
			// The process repeats itself again until the whole file is read
			nextLine = br.readLine();
		}
		br.close();
	}
	
	/**
	 * @return	-	Returns the movieList ArrayList<Movie>
	 */
	public ArrayList<Movie> getMovieList()
	{
		return movieList;
	}
	
	/*
	 * This method will read in the ArrayList created by the movieDataBase method. It will then search
	 * the list based on what the user inputs from the Driver class. The results will then be transfered to an
	 * ArrayList of type String and then be shown to the user.
	 * 
	 * @param:
	 * userInput - The input which the user has inputed based on what they want to search for
	 * 
	 * @return:
	 * An ArrayList of type String which will then be sent to be shown via GUI window.
	 */
	public ArrayList<String> movieDataReader(String[] a)
	{
		ArrayList<String> positiveList = new ArrayList<String>();
		
		if (!title.equals(null) && year.equals(null))
		{
		// For each loop will search the movie list and then store the matching values into a new ArrayList.
		for(Movie movieFound : movieList)
		{
			// This will be the comparison conditional being used. This also will take into account the 
			// case sensitive aspect of the user input.
			if(movieFound.getMovieName().contains(title) || movieFound.getMovieName().toLowerCase().contains(title.toLowerCase()))
			{
				positiveList.add(movieFound.toString());
			}
		}
		}
		
		//year search
		if (!year.equals(null) && title.equals(null))
		{
			for(Movie movieFound : movieList)
			{
				// This will be the comparison conditional being used. This also will take into account the 
				// case sensitive aspect of the user input.
				if(movieFound.getMovieYear().contains(year))
				{
					positiveList.add(movieFound.toString());
				}
			}
		}
		
		if (!title.equals(null) && !year.equals(null))
		{
			
		}
		return positiveList;
	}
}
