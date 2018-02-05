import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Project #2
 * CS 2334, Section 010 - Lab 13
 * Hunter Black, Andrew Easley, Zhendong Sun
 * March 28, 2016
 * <P>
 * This is the driver class for the program, contains the main method. It creates an 
 * ArrayList of Movie objects, an ArrayList of Series objects, and an ArrayList of Object 
 * objects. Uses inputStream to read input from the user. Contains methods to search the
 * ArrayLists of Series and Movie objects to see if user input matches any information of the
 * objects in the list. Contains numerous sort methods for sorting the ArrayLists in a variety
 * of ways, by title and by year. Contains a save method used to save sorted information into
 * a text file.
 * </P>
 * @version 1.0
 */
public class Driver 
{
	/** ArrayList of Movie objects, each element contains a Movie object containing
	 * information about the movie such as  movie title, year released along with a Roman numeral
	 * to indicate which movie it is if multiple movies with the same name were released during
	 * the year, whether it went to video, theaters or aired on TV, and the year that it was 
	 * released
	 */
	public static ArrayList<Movie> movieList;

	public static ArrayList<Movie> movieL;

	/** ArrayList of Series objects, each element contains a Series object containing
	 * information about the series such as series title, year the series debutted, the year
	 * the episode was released, the title of the episode, the season the episode was in, and
	 * the episode number
	 */
	public static ArrayList<Series> seriesList;

	public static ArrayList<Series> seriesL;

	/** ArrayList of Object objects, each element contains a Object object either being a Movie
	 * object or Series object, used when both want to be sorted and searched through at the same
	 * time
	 */
	public static ArrayList<Object> bothMS;

	public static ArrayList<Episode> tvList;
	
	public static ArrayList<String> display;
	
	private static Database pdb;


	/* String used for user input, used to search for partial or complete matches in the lists*/
	private static String userInput;

	/**
	 * Main method to run program. Reads information into String using inputStream so that the 
	 * user can search through the ArrayList for partial or complete matches. Asks the user a
	 * series of questions so that the program can determine which ArraysLists to sort, how
	 * to sort them, to use partial or complete matches
	 * @param args Program argument unused
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		movieList = new ArrayList<Movie>();
		seriesList = new ArrayList<Series>();
		tvList = new ArrayList<Episode>();
		bothMS = new ArrayList<Object>();
		movieL = new ArrayList<Movie>();	
		seriesL = new ArrayList<Series>();
		
		//ArrayList of String representations of found people matching search
		display = new ArrayList<String>();
		//Name holder for proper display at the top 
		String name = "";
		//Database holding MediaMaker objects
		pdb = new Database(1);
		//80 equals signs for separating search parameters from display info
		String equalsStuff = "=";
		for (int i = 0; i < 80; ++i)
		{
			equalsStuff += "=";
		}
		String binaryFile = "";
		ArrayList<MediaMaker> mediaMakerMatches = new ArrayList<MediaMaker>();
		
		//This is used to jump to the end of the program if searching by exact name when searching MediaMakers
		//and the name isn't a key for the LinkedList
		int thereOrNot = 0;

		boolean flag = true;
		boolean episodeOrNot = false;
		ArrayList<String> movieInfo = new ArrayList<String>();
		ArrayList<String> seriesInfo = new ArrayList<String>();
		BufferedReader inputReader = new BufferedReader(new InputStreamReader( System.in ) );

		System.out.println("Read (t)ext or (b)inary data?");
		userInput = inputReader.readLine();
		if (userInput.equalsIgnoreCase("t"))
		{
			System.out.println("Enter the name of the file containing movie information");
				userInput = inputReader.readLine();
				Movie.readFile(movieInfo, userInput);
			System.out.println("Enter the name of the file containing TV information");
				userInput = inputReader.readLine();
				Series.readFile(seriesInfo, userInput);
			System.out.println("Enter the name of the file containing Actor information");
				userInput = inputReader.readLine();
				pdb.parseActor(userInput);
			System.out.println("Enter the name of the file containing Director information");
				userInput = inputReader.readLine();
				pdb.parseDirector(userInput);
			System.out.println("Enter the name of the file containing Producer information");
				userInput = inputReader.readLine();
				pdb.parseProducer(userInput);
		}
		else if (userInput.equalsIgnoreCase("b"))
		{
			System.out.println("Enter the name of the binary file");
			userInput = inputReader.readLine();
			binaryFile = load(userInput);
		}


		Movie.parseMovie(movieInfo, movieList);
		Series.parseSeries(seriesInfo, seriesList, tvList);
		
		while (flag != false)
		{
		System.out.println("Search through (m)edia or (p)eople?");
		userInput = inputReader.readLine();
		
		//If searching by Media, enter the loop similar to as seen in 
		//project 2 with exception of saving at the end to either
		//text or binary format
		if (userInput.equalsIgnoreCase("m"))
		{
		System.out.println("Would you like to search (m)ovies, (s)eries, or (b)oth?" );
		userInput = inputReader.readLine();

			episodeOrNot = false;
			if(userInput.equals("m")) {

				System.out.println("Search (t)itle, (y)ear, or (b)oth?");
				userInput = inputReader.readLine();

				if(userInput.equals("t")) {

					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMovieExact(movieList, userInput);
						userInput = "";
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMoviePartial(movieList, userInput);
						userInput = "";
					}
				}
				else if(userInput.equals("y")) {
					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchMovieExact(movieList, userInput);
					//userInput = "";
				}
				else if(userInput.equals("b")) {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMovieExact(movieList, userInput);
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMovieExact(movieList, userInput);
					}
					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchMovieExact(movieList, userInput);
					//userInput = "";
				}

			}
			else if(userInput.equals("s")) {
				System.out.println("Search (t)itle, (y)ear, or (b)oth?");
				userInput = inputReader.readLine();
				if(userInput.equals("t")) {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchSeriesExact(seriesList, userInput);
						//userInput = "";
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchSeriesPartial(seriesList, userInput);
					//	userInput = "";
					}

					System.out.println("Include episode titles in search and output (y/n)?");
					userInput = inputReader.readLine();
					if(userInput.equals("y")) {
						episodeOrNot = true;
					}
				}
				else if(userInput.equals("y")) {
					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchSeriesExact(seriesList, userInput);
					//userInput = "";
				}
				else if(userInput.equals("b")) {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchSeriesExact(seriesList, userInput);
						//userInput = "";
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchSeriesPartial(seriesList, userInput);
						//userInput = "";
					}

					System.out.println("Include episode titles in search and output (y/n)?");
					userInput = inputReader.readLine();
					if(userInput.equals("y")) {
						episodeOrNot = true;
					}

					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchSeriesExact(seriesList, userInput);
				}
			}
			else if(userInput.equals("b")) {
				System.out.println("Search (t)itle, (y)ear, or (b)oth?");
				userInput = inputReader.readLine();
				if(userInput.equals("t")) {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMovieExact(movieList, userInput);
						searchSeriesExact(seriesList, userInput);
						//userInput = "";
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMoviePartial(movieList, userInput);
						searchSeriesPartial(seriesList, userInput);
						//userInput = "";
					}

					System.out.println("Include episode titles in search and output (y/n)?");
					userInput = inputReader.readLine();
					if(userInput.equals("y")) {
						episodeOrNot = true;
					}
				}
				else if(userInput.equals("y")) {
					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchMovieExact(movieList, userInput);
					searchSeriesExact(seriesList, userInput);
					//userInput = "";
				}
				else if(userInput.equals("b")) {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userInput = inputReader.readLine();
					if(userInput.equals("e")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMovieExact(movieList, userInput);
						searchSeriesExact(seriesList, userInput);
						//userInput = "";
					}
					else if(userInput.equals("p")) {
						System.out.println("Title?");
						userInput = inputReader.readLine();
						searchMoviePartial(movieList, userInput);
						searchSeriesPartial(seriesList, userInput);
						//userInput = "";
					}

					System.out.println("Include episode titles in search and output (y/n)?");
					userInput = inputReader.readLine();
					if(userInput.equals("y")) {
						episodeOrNot = true;
					}

					System.out.println("Year?");
					userInput = inputReader.readLine();
					searchMovieExact(movieList, userInput);
					searchSeriesExact(seriesList, userInput);
					//userInput = "";
				}
			}
		}
		
//			System.out.println("Sort by(t)itle or (y)ear?");
//			userInput = inputReader.readLine();
//			if(userInput.equals("t")) {
//				sortMovieTitle(movieList);
//				sortSeriesTitle(seriesList);
//			}
//			else if(userInput.equals("y")) {
//				sortMovieYear(movieList);
//				sortSeriesYear(seriesList);
//			}
		
			
			//Searching through people instead of media, which will go through the linked hash map
			//of MediaMakers to look for a match
			else if (userInput.equalsIgnoreCase("p"))
			{
				System.out.println("Search by (e)xact or (p)artial name?");
				userInput = inputReader.readLine();
				if (userInput.equalsIgnoreCase("e"))
				{
					System.out.println("Enter the name of the MediaMaker you wish to search for (Last name, first name)");
					userInput = inputReader.readLine();
					name = userInput;
					thereOrNot = pdb.searchPeopleExact(userInput, display, mediaMakerMatches);
					
					if (display.size() != 1)
					{
					if(thereOrNot != 1)
					{
						String peopleSearch = "SEARCHED PEOPLE \n" + "EXACT NAME: " + name + "\n" + equalsStuff;
						System.out.println(peopleSearch);
						for (int i = 0; i < display.size(); ++i)
						{
							System.out.println(display.get(i));
						}
					}
					}
					else if (display.size() ==1 )
					{
						if (thereOrNot != 1)
						{
						System.out.println("Display (t)ext or (g)raph?");
						userInput = inputReader.readLine();
						if (userInput.equalsIgnoreCase("t"))
						{
							String peopleSearch = "SEARCHED PEOPLE \n" + "EXACT NAME: " + name + "\n" + equalsStuff;
							System.out.println(peopleSearch);
							for (int i = 0; i < display.size(); ++i)
							{
								System.out.println(display.get(i));
							}
						}
						else if (userInput.equalsIgnoreCase("g"))
						{
							System.out.println("(P)ie chart or (H)istogram");
							userInput = inputReader.readLine();
							if (userInput.equalsIgnoreCase("p"))
							{
								piChart pi = new piChart();
								pi.setData(mediaMakerMatches.get(0));
							}
						}
						}
					}
				}
				
				else if (userInput.equalsIgnoreCase("p"))
				{
					System.out.println("Enter the name of the MediaMaker you wish to search for (Last name, first name)");
					userInput = inputReader.readLine();
					name = userInput;
					thereOrNot = pdb.searchPeoplePartial(userInput, display, mediaMakerMatches);
					
					if (display.size() != 1)
					{
					if (thereOrNot != 1)
					{
						String peopleSearch = "SEARCHED PEOPLE \n" + "PARTIAL NAME: " + name + "\n" + equalsStuff;
						System.out.println(peopleSearch);
						for (int i = 0; i < display.size(); ++i)
						{
							System.out.println(display.get(i));
						}
					}
					}
					else if (display.size() == 1)
					{
						System.out.println("Display (t)ext or (g)raph?");
						userInput = inputReader.readLine();
						if (userInput.equalsIgnoreCase("t"))
						{
							String peopleSearch = "SEARCHED PEOPLE \n" + "PARTIAL NAME: " + name + "\n" + equalsStuff;
							System.out.println(peopleSearch);
							for (int i = 0; i < display.size(); ++i)
							{
								System.out.println(display.get(i));
							}
						}
						else if (userInput.equalsIgnoreCase("g"))
						{
							System.out.println("(p)ie chart or (h)istogram?");
							userInput = inputReader.readLine();
							//TODO implement graphical stuff here
						}
					}
				}
				
			}
			
		//Save media (searching for Movies, Series, Episodes)
			if (thereOrNot != 1 && display.size() == 0)
			{
			System.out.println("Save(y/n)?");
			userInput = inputReader.readLine();
			if(userInput.equalsIgnoreCase("y")) {
				System.out.println("Save data to (t)ext or (b)inary?");
				userInput = inputReader.readLine();
				//Saving to text file
				if (userInput.equalsIgnoreCase("t"))
				{
				if(episodeOrNot == true) {
					System.out.println("What is the name of the file?");
					userInput = inputReader.readLine();
					save(movieL, seriesL, userInput, episodeOrNot);
				}
				else {
					System.out.println("What is the name of the file?");
					userInput = inputReader.readLine();
					save(movieL, seriesL,  userInput, episodeOrNot);
				}
				}
				//Saving to binary file
				else if (userInput.equalsIgnoreCase("b"))
				{
					System.out.println("Enter the name of the file to save to:");
					binaryFile = inputReader.readLine();
					for (int i = 0; i < movieL.size(); ++i)
					{
						Movie.writeMovie(binaryFile, movieL.get(i));
					}
					for (int i = 0; i < seriesL.size(); ++i)
					{
						Series.writeSeries(binaryFile, seriesL.get(i));
					}
				}
			}
			}
			
			//Save MediaMaker information
			else if (thereOrNot != 1 && display.size() != 0)
			{
				System.out.println("Save (y/n)?");
				userInput = inputReader.readLine();
				if (userInput.equalsIgnoreCase("y"))
				{
					System.out.println("Save data to (t)ext or (b)inary?");
					userInput = inputReader.readLine();
					if (userInput.equalsIgnoreCase("t"))
					{
						System.out.println("Enter the name of the text file to save to:");
						userInput = inputReader.readLine();
						saveData(userInput, display);
					}
					else if (userInput.equalsIgnoreCase("b"))
					{
						System.out.println("Enter the name of the binary file to save to: ");
						binaryFile = inputReader.readLine();
						for (int i = 0; i < mediaMakerMatches.size(); ++i)
						{
							MediaMaker.writeMediaMaker(binaryFile, mediaMakerMatches.get(i));
						}
					}
				}
			}
			
			System.out.println("Continue (y/n)?");
			userInput = inputReader.readLine();
			if(userInput.equals("n")) {
				flag = false;
			}
			thereOrNot = 0;
		}
		
		System.out.println("Thank you, goodbye");
		System.exit(0);
	}	
	

	/**
	 * A method used to search the ArrayList of Movie objects for a movie with the exact title 
	 * that the user enters
	 * @param movieList An  ArrayList of Movie objects, each element contains a Movie object 
	 * containing information about the movie such as title, year released along with 
	 * a Roman numeral to indicate which movie it is if multiple movies with the same name
	 * were released, whether it went to video, theaters or aired on TV, and the year 
	 * that it was released
	 * @param userInput A variable used to store the value of a movie title the user wishes
	 * to search the ArrayList for (exact matches)
	 * @return The method has a void return type because the method searches if there are any
	 * matches (exact) to the movie title the user entered in the ArrayList
	 * of Movie objects
	 * <dd>PRE - There must be an ArrayList of Movie objects and a String variable containing
	 * the title of a movie the user wishes to search for
	 * <dd>POST - The method searches through the ArrayList to see if any of the Movie objects
	 * have the same title that the user input to the method and prints out whether or not it
	 * does
	 */
	public static void searchMovieExact(ArrayList<Movie> movieList, String userInput) {
		
		Movie movie = new Movie(userInput, "", "");
		int temp;
		temp = Collections.binarySearch(movieList, movie);
		if(temp >= 0) {
			System.out.println(movie.getMovieTitle());
			movieL.add(movie);
		}
		
	}

	/**
	 * A method used to search the ArrayList of Movie objects for movies with partially
	 * matching titles that the user enters
	 * @param movieList An  ArrayList of Movie objects, each element contains a Movie object 
	 * containing information about the movie such as title, year released along with 
	 * a Roman numeral to indicate which movie it is if multiple movies with the same name
	 * were released, whether it went to video, theaters or aired on TV, and the year 
	 * that it was released
	 * @param userInput A variable used to store the value of a movie title the user wishes
	 * to search the ArrayList for (partial matches)
	 * @return The method has a void return type because the method searches if there are any
	 * matches (partial) to the movie title the user entered in the ArrayList
	 * of Movie objects
	 * <dd>PRE - There must be an ArrayList of Movie objects and a String variable containing
	 * the title of a movie the user wishes to search for
	 * <dd>POST - The method searches through the ArrayList to see if any of the Movie objects
	 * have the same title that the user input to the method and prints out whether or not it
	 * does
	 */
	public static void searchMoviePartial(ArrayList<Movie> movieList, String userInput) {
		int count = 0;
		while(count < movieList.size()) {
			if(movieList.get(count).getMovieTitle().contains(userInput)) {
				System.out.println(movieList.get(count).getMovieTitle());
				movieL.add(movieList.get(count));
			}
			count++;
		}
	}

	/**
	 * A method used to search the ArrayList of Series objects for a series with the information
	 * that the user enters exactly
	 * @param movie An  ArrayList of Series objects, each element contains a Series object 
	 * containing information about the series such as title, debut year, episode release year,
	 * episode title, season number and episode number
	 * @param userInput A variable used to store the value of a String of series information
	 * the user wishes to search the ArrayList for (exact matches)
	 * @return The method has a void return type because the method searches if there are any
	 * matches (exact) to the series the user entered in the ArrayList
	 * of Series objects
	 * <dd>PRE - There must be an ArrayList of Series objects and a String variable containing
	 * the series the user wishes to search for
	 * <dd>POST - The method searches through the ArrayList to see if any of the Series objects
	 * have the same information that the user input to the method and prints out whether or
	 * not it does
	 */
	public static void searchSeriesExact(ArrayList<Series> seriesList, String userInput) {
		Series series = new Series(userInput, "", "", null);
		int temp;
		temp = Collections.binarySearch(seriesList, series);
		if(temp >= 0) {
			System.out.println(series.getSeriesTitle());
			seriesL.add(series);
		}
	}

	/**
	 * A method used to search the ArrayList of Series objects for a series with the information
	 * that the user enters partially
	 * @param movie An  ArrayList of Series objects, each element contains a Series object 
	 * containing information about the series such as title, debut year, episode release year,
	 * episode title, season number and episode number
	 * @param userInput A variable used to store the value of a String of series information
	 * the user wishes to search the ArrayList for (partial matches)
	 * @return The method has a void return type because the method searches if there are any
	 * matches (partial) to the series the user entered in the ArrayList
	 * of Series objects
	 * <dd>PRE - There must be an ArrayList of Series objects and a String variable containing
	 * the series the user wishes to search for
	 * <dd>POST - The method searches through the ArrayList to see if any of the Series objects
	 * have the same information that the user input to the method and prints out whether or
	 * not it does
	 */
	public static void searchSeriesPartial(ArrayList<Series> seriesList, String userInput) {
		int count = 0;
		while(count < seriesList.size()) {
			if(seriesList.get(count).getSeriesTitle().contains(userInput)) {
				System.out.println(seriesList.get(count).getSeriesTitle());
				seriesL.add(seriesList.get(count));
			}
			count++;
		}
	}

	/**
	 * A method used to sort the ArrayList of Movie objects by title in alphabetical order
	 * @param movieList An ArrayList of Movie objects, each a different movie from a text
	 * file
	 * @return The method has a void return type because the method just sorts the ArrayList
	 * of Movies by title in alphabetical order
	 * <dd>PRE - There must be an ArrayList of Movie objects to be sorted
	 * <dd>POST - The method sorts the ArrayList in alphabetical order using the using the 
	 * compareTo method
	 */
	public static void sortMovieTitle(ArrayList<Movie> movieList) {
		Collections.sort(movieList);
	}

	/**
	 * A method used to sort the ArrayList of Series objects by title in alphabetical order
	 * @param seriesList An ArrayList of Series objects, each a different series from a text
	 * file
	 * @return The method has a void return type because the method just sorts the ArrayList
	 * of Series by title in alphabetical order
	 * <dd>PRE - There must be an ArrayList of Series objects to be sorted
	 * <dd>POST - The method sorts the ArrayList in alphabetical order using the using the 
	 * compareTo method
	 */
	public static void sortSeriesTitle(ArrayList<Series> seriesList) {
		Collections.sort(seriesList);
	}

	/**
	 * A method used to sort the ArrayList of Movie objects by year in normal numerical
	 * ordering
	 * @param movieList An ArrayList of Movie objects, each a different movie from a text
	 * file
	 * @return The method has a void return type because the method just sorts the ArrayList
	 * of Movies by the normal numerical ordering
	 * <dd>PRE - There must be an ArrayList of Movie objects to be sorted
	 * <dd>POST - The method sorts the ArrayList in numerical order by year using the using the 
	 * MOVIE_YEAR_COMPARATOR subclass in the Movie class
	 */
	public static void sortMovieYear(ArrayList<Movie> movieList) {
		Collections.sort(movieList, Movie.MOVIE_YEAR_COMPARATOR);
	}

	/**
	 * A method used to sort the ArrayList of Series objects by year in normal numerical
	 * ordering
	 * @param seriesList An ArrayList of Series objects, each a different series from a text
	 * file
	 * @return The method has a void return type because the method just sorts the ArrayList
	 * of Series objects by the normal numerical ordering
	 * <dd>PRE - There must be an ArrayList of Series objects to be sorted
	 * <dd>POST - The method sorts the ArrayList in numerical order by year using the using the 
	 * SERIES_YEAR_COMPARATOR subclass in the Series class
	 */
	public static void sortSeriesYear(ArrayList<Series> seriesList) {
		Collections.sort(seriesList, Series.SERIES_YEAR_COMPARATOR);
	}

	/**
	 * Method used to save sorted information in an ArrayList of Object objects onto a text file,
	 * elements can be either Series or Movie objects sorted by either title or year depending
	 * on the choice of the user
	 * @param userInput 
	 * @param An ArayList of Object objects containing either Movie or Series objects in sorted
	 * order to be saved to a text file
	 * @throws IOException 
	 */
	public static void save(ArrayList<Movie> movieL, ArrayList<Series> seriesL, 
			String userInput, boolean episodeOrNot) throws IOException {
		FileWriter outfile = new FileWriter(userInput);
		BufferedWriter buffWrite = new BufferedWriter(outfile);
		if(episodeOrNot == true) {
			//Saves Movie items
			for(int counter = 0; counter < movieL.size(); counter++) {
				buffWrite.write("MOVIE: " + movieL.get(counter).toString());
				buffWrite.newLine();
			}
			//Saves Series items
			for(int counter = 0; counter < seriesL.size(); counter++) {
				buffWrite.write("SERIES: " + seriesL.get(counter).toString());
				buffWrite.newLine();
				ArrayList<Episode> tempList = seriesL.get(counter)
						.getEpisodes();
				//Saves corresponding Episode items
				for(int counting = 0; counting < tempList.size(); ++counting){
					buffWrite.write("EPISODE: " + tempList.get(counting).toString());
					buffWrite.newLine();
				}
			}			
		}
		else {
			//Saves Movie items
			for(int counter = 0; counter < movieL.size(); counter++) {
				buffWrite.write("MOVIE: " + movieL.get(counter).toString());
				buffWrite.newLine();
			}
			//Saves Series items
			for(int counter = 0; counter < seriesL.size(); counter++) {
				buffWrite.write("SERIES: " + seriesL.get(counter).toString());
				buffWrite.newLine();
			}
		}
		buffWrite.close();

	}
	
	public static void saveData(String fileName, ArrayList<String> display) throws IOException{
		FileWriter outfile = new FileWriter(userInput);
		BufferedWriter buffWrite = new BufferedWriter(outfile);
		String list = "";
		for (String a : display)
		{
			list += a + "\n";
		}
		buffWrite.write(list);
		buffWrite.close();
	}

	public static String load(String userInput) throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(userInput);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		String output = (String) objectInputStream.readObject();
		objectInputStream.close();
		return output;
		
	}
	




}