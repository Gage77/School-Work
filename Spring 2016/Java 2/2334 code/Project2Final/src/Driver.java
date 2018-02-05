import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Project #2 with Eclipse as the IDE
 * CS 2334, Section 013
 * Hunter Black, Eric Morales, Ramon Valenzuela
 * 2/19/16
 * <P>
 * This class creates a simple media database system allowing 
 * users to search through a list of movies and TV episodes/series
 */

public class Driver 
{
	/**
	 * This is the main method of this program, executing the dialog with the user,
	 * asking questions, displaying info, and performing file saving functions if 
	 * prompted
	 * 
	 * @param args		Program arguments 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		String saveFile;
		ArrayList<String> displayMovie = new ArrayList<String>(); //list containing info to display to user on movie
		ArrayList<String> displaySeries = new ArrayList<String>(); //list containing info to display to user on series/episode
		
		//Beginning dialogue
		JOptionPane.showMessageDialog(null, "Welcome to the MDb simple user interface system. \n"
				+ "This system allows you to search our database \n of movies and TV series/episodes to learn information "
				+ "about them. Click Ok to \n enter begin.");
						
		
		//Next block will get file information from user and send those filenames to the specified
		//methods in (Movie?) and (TvSeries?) classes to be parsed
		//Getting movie info from user
		String movieFile = JOptionPane.showInputDialog(null, "Enter name of file holding movie data:");
		//TODO send movieFile to Movie class		
		MovieDataBase movieDataBase = new MovieDataBase(movieFile);
		//Getting series info from user
		String seriesFile = JOptionPane.showInputDialog(null, "Enter name of file holding series/episode data:");	
		//TODO send seriesFile to Series class
		TvDataBase tvDatabase = new TvDataBase(seriesFile);
		
		
		String userInput = "y";
		
		/**
		 * Array positions are as follows:
		 * 0: search by movie, series, or both
		 * 1: search by title, year, or both
		 * 2: search by exact or partial match title
		 * 3: include episode title in search and display
		 * 4: title
		 * 5: year
		 * 6: sort type
		 */
		String[] questions = new String[7];
		
		//JOptionPane display stuff
		String displaySearchType = ""; //SEARCHED (MOVIE/TV SERIES AND TV EPISODES/MOVIES, TV SERIES, AND TV EPISODES)
		String exactPartial = ""; //(EXACT/PARTIAL) TITLE: 
		String displaySortType = ""; //SORTED BY (TITLE/YEAR)
		String equalsstuff = "="; //80 equals signs
		
		//Creates 80 "=" signs
		for (int i = 0; i < 80; ++i)
		{
			equalsstuff = equalsstuff + "=";
		}
		
		
		//While loop for the MDb user interface for searching/displaying info
		while (userInput.equalsIgnoreCase("y"))
		{
			//This next block of code will ask the user questions and put their answers into the above strings which will
			//be used to determine which search method will be implemented
			questions[0] = JOptionPane.showInputDialog(null, "Search by (m)ovies, (s)eries, or (b)oth?");
			//Next conditional checks to see if the user clicked "X", "Cancel", or didn't enter any data.
			//If so, the program gracefully exits. This is done after every JOptionPane dialog from here
			//on out
			if (questions[0] == null)
			{
				System.exit(0);
			}
			if (questions[0].equalsIgnoreCase("m"))
				displaySearchType = "SEARCHED MOVIES";
			else if (questions[0].equalsIgnoreCase("s"))
				displaySearchType = "SEARCHED TV SERIES AND TV EPISODE";
			else if (questions[0].equalsIgnoreCase("b"))
				displaySearchType = "SEARCHED MOVIES, TV SERIES, AND TV EPISODE";
			
			//Title, Year, Both
			questions[1] = JOptionPane.showInputDialog(null, "Search by (t)itle, (y)ear, or (b)oth?");
			if (questions[1] == null)
			{
				System.exit(0);
			}
			
			//Exact, Partial match
			if (questions[1].equalsIgnoreCase("t") || questions[1].equalsIgnoreCase("b"))
			{
				questions[2] = JOptionPane.showInputDialog(null, "Search for (e)xact or (p)artial matches?");
				if (questions[2] == null)
				{
					System.exit(0);
				}
				if (questions[2].equalsIgnoreCase("e"))
					exactPartial = "EXACT TITLE: ";
				else if (questions[2].equalsIgnoreCase("p"))
					exactPartial = "PARTIAL TITLE: ";
			}
			
			//Include episode titles in search and output
			if (questions[1].equalsIgnoreCase("s") || questions[0].equalsIgnoreCase("b"))
			{
				if (questions[1].equalsIgnoreCase("t") || questions[0].equalsIgnoreCase("b"))
					questions[3] = JOptionPane.showInputDialog(null, "Include episode titles in search and output (y/n)?");
					if (questions[3] == null)
					{
						System.exit(0);
					}
			}
			
			//Ask for title
			if (questions[1].equalsIgnoreCase("t") || questions[1].equalsIgnoreCase("b"))
			{
				questions[4] = JOptionPane.showInputDialog(null, "Title?");
				if (questions[4] == null)
				{
					System.exit(0);
				}
			}
			
			//Ask for year
			if (questions[1].equalsIgnoreCase("y") || questions[1].equalsIgnoreCase("b"))
			{
				questions[5] = JOptionPane.showInputDialog(null, "Year (single, multiple seperated by commas, or range seperated by hyphon)?");
				if (questions[5] == null)
				{
					System.exit(0);
				}
			}
			
			
			//Ask for sort type
			questions[6] = JOptionPane.showInputDialog(null, "Sort by (t)itle or (y)ear");
			if (questions[6] == null)
			{
				System.exit(0);
			}
			if (questions[6].equalsIgnoreCase("t"))
				displaySortType = "SORTED BY TITLE";
			else if (questions[6].equalsIgnoreCase("y"))
				displaySortType = "SORTED BY YEAR";
		
			
			//Depending on question answers, these next conditionals will navigate to the correct search method and 
			//Send the correct parameters to that search method
			//This is assuming the search methods will return an ArrayList<String> which is put into either
			//displayMovie or displaySeries
			if (questions[0].equals("m") || questions[0].equals("b"))
			{
				displayMovie = movieDataBase.movieDataReader(questions);
			}
			else if  (questions[0].equals("s") || questions[0].equals("b"))
			{
				displaySeries = tvDatabase.tvDataSearcher(questions);
			}
			
			
			//Creates a single string from the displayMovie with a newline after each entry
			String resultM = "";
			for (String b : displayMovie)
			{
				resultM += b.toString() + "\n";
			}
			//Creates a single string from the displaySeries with a newline after each entry
			String resultS = "";
			for (String c : displaySeries)
			{
				resultS += c.toString() + "\n";
			}

			
			//Display answers to user in specified manner found on Project2 PDF
			//If searching by movie
			if (questions[0].equalsIgnoreCase("m"))
			{
				//If searching movies by title
				if (questions[1].equalsIgnoreCase("t"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] +  "\n"
							+ "YEARS: ANY \n" + displaySortType + "\n" + equalsstuff + "\n" + resultM);
				}
				//If searching movies by year
				else if (questions[1].equalsIgnoreCase("y"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + "TITLE: ANY \n" + "YEARS: "
								+ questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultM);
				}
				//If searching movies by both year and title
				else if (questions[1].equalsIgnoreCase("b"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] + "\n" 
							+ "YEARS: " + questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultM);
				}
			}
			//If searching by series
			else if (questions[0].equalsIgnoreCase("s"))
			{
				//If searching series by title
				if (questions[1].equalsIgnoreCase("t"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] +  "\n"
							+ "YEARS: ANY \n" + displaySortType + "\n" + equalsstuff + "\n" + resultS);
				}
				//If searching series by year
				else if (questions[1].equalsIgnoreCase("y"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + "TITLE: ANY \n" + "YEARS: "
								+ questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultS);
				}
				//If searching series by both year and title
				else if (questions[1].equalsIgnoreCase("b"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] + "\n" 
							+ "YEARS: " + questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultS);
				}
			}
			//If searching for both
			else if (questions[0].equalsIgnoreCase("b"))
			{
				//If searching both movies and series by title
				if (questions[1].equalsIgnoreCase("t"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] +  "\n"
							+ "YEARS: ANY \n" + displaySortType + "\n" + equalsstuff + "\n" + resultM + resultS);
				}
				//If searching both movies and series by year
				else if (questions[1].equalsIgnoreCase("y"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + "TITLE: ANY \n" + "YEARS: "
							+ questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultM + resultS);
				}
				//If searching both movie and series by both title and year
				else if (questions[1].equalsIgnoreCase("b"))
				{
					JOptionPane.showMessageDialog(null, displaySearchType + "\n" + exactPartial + questions[4] + "\n" 
							+ "YEARS: " + questions[5] + "\n" + displaySortType + "\n" + equalsstuff + "\n" + resultM + resultS);
				}
			}
			
			
			
			//Optional save data to file
			userInput = JOptionPane.showInputDialog(null, "Save data to file (y/n)?");
			if (userInput == null)
			{
				System.exit(0);
			}
			if (userInput.equalsIgnoreCase("y"))
			{
				//Write data to specified file using the FileWriter and BufferedWriter objects
				//Tested and does work
				saveFile = JOptionPane.showInputDialog(null, "Enter the name of the file you wish to save data to:");	
				//Creates file of name saveFile
				FileWriter outfile = new FileWriter(saveFile);
				BufferedWriter bw = new BufferedWriter(outfile);
				
				//Check to see what ArrayList to get info from
				if (questions[0].equals("m") || questions[0].equals("b"))
				{
					for (int i = 0; i < displayMovie.size(); ++i)
					{
						//Writes each element in displayMovie to file saveFile
						bw.write(displayMovie.get(i).toString());
						bw.newLine();
					}
				}
				//Same as above. It is an if so the "b" will work (i.e. enter both conditionals if question1 = "b")
				if (questions[0].equals("s") || questions[0].equals("b"))
				{
					for (int i = 0; i < displaySeries.size(); ++i)
					{
						//Writes each element in displaySeries to file saveFile
						bw.write(displaySeries.get(i).toString());
						bw.newLine();
					}
				}
				//This command saves the file
				bw.close();
				JOptionPane.showMessageDialog(null, "Saved");
			}
			//Prompt for sentinel
			userInput = JOptionPane.showInputDialog(null, "Continue searching (y/n)?");
			if (userInput == null)
			{
				System.exit(0);
			}
		}
		System.exit(0);
	}
}
