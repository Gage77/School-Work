import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * </P>
 * @version 1.0
 */
public class Driver 
{	
	/**
	 * Main method for the program, setting up interface for user to 
	 * search for media from provided database
	 * 
	 * @param args		Contains command line arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String saveFile;
		ArrayList<String> displayMovie = new ArrayList<String>(); //list containing info to display to user on movie
		ArrayList<String> displaySeries = new ArrayList<String>(); //list containing info to display to user on series/episode
		//This is the "Scanner" for this program, taking input from the reader
		BufferedReader inputR = new BufferedReader(new InputStreamReader(System.in));
		
		JOptionPane.showMessageDialog(null, "Welcome to the MDb simple user interface system. \n"
				+ "This system allows you to search our database \n of movies and TV series/episodes to learn information "
				+ "about them. Click Ok to \n enter begin.");
				
		
		//Getting movie info from user
		String movieFile = JOptionPane.showInputDialog(null, "Enter name of file holding movie data:");
		//TODO send movieFile to Movie class		
		
		//Getting series info from user
		String seriesFile = JOptionPane.showInputDialog(null, "Enter name of file holding series/episode data:");	
		//TODO send seriesFile to Series class
		TvDataBase tvDatabase = new TvDataBase(seriesFile);
		
		
		String userInput = "y";
		String question1 = null; //search object type (m, s, b)
		String question2 = null; //search parameter type (t, y, b)
		String question3 = null; //exact or partial match, iff searching by title or title/year (e, p)
		String question4 = null; //include episode title in search/output iff searching for series or both and by title or both (y, n)
		String title = null; //title to search by
		String episodeTitle = null;
		String year = null; //year to search by 
		String sortType = null; //how to sort results
		
		//While loop for the MDb user interface for searching/displaying info
		while (userInput.equalsIgnoreCase("y"))
		{
			//This next block of code will ask the user questions and put their answers into the above strings which will
			//be used to determine which search method will be implemented
			question1 = JOptionPane.showInputDialog(null, "Search by (m)ovies, (s)eries, or (b)oth?");			
			question2 = JOptionPane.showInputDialog(null, "Search by (t)itle, (y)ear, or (b)oth?");
			
			if (question2.equalsIgnoreCase("t") || question2.equalsIgnoreCase("b"))
			{
				question3 = JOptionPane.showInputDialog(null, "Search for (e)xact or (p)artial matches?");
			}
			
			if ((question1.equalsIgnoreCase("s") || question1.equalsIgnoreCase("b")) && (question2.equalsIgnoreCase("t") || question2.equalsIgnoreCase("b")))
			{
				question4 = JOptionPane.showInputDialog(null, "Include episode titles in search and output (y/n)?");
			}
			
			if ((question2.equalsIgnoreCase("t") || question2.equalsIgnoreCase("b")) && question4.equalsIgnoreCase("y"))
			{
				title = JOptionPane.showInputDialog(null, "Main title?");
				episodeTitle = JOptionPane.showInputDialog(null, "Episode title?");
			}
			
			{
				title = JOptionPane.showInputDialog(null, "Title?");
			}
			
			if (question2.equalsIgnoreCase("t") || question2.equalsIgnoreCase("b"))
			{
				title = JOptionPane.showInputDialog(null, "Title?");
			}
			
			if (question2.equalsIgnoreCase("y") || question2.equalsIgnoreCase("b"))
			{
				year = JOptionPane.showInputDialog(null, "Year (single, multiple seperated by commas, or range seperated by hyphon)?");
			}
			
			sortType = JOptionPane.showInputDialog(null, "Sort by (t)itle or (y)ear");
		
			
			//Depending on question answers, these next conditionals will navigate to the correct search method and 
			//Sent the correct parameters to that search method
			//This is assuming the search methods will return an ArrayList<String> which is put into either
			//displayMovie or displaySeries
			if (question1.equals("m") || question1.equals("b"))
			{
				if (question2.equals("t"))
				{
					displayMovie = MovieDatabase.searchForMovie(title, sortType, question3);
				}
				else if (question2.equals("y"))
				{
					displayMovie = MovieDatabase.searchForMovie(year, sortType, question3);
				}
				else if (question2.equals("b"))
				{
					displayMovie = MovieDatabase.searchForMovie(title, year, sortType, question3);
				}
			}
			else if  (question1.equals("s") || question1.equals("b"))
			{
				if (question2.equals("t"))
				{
					displaySeries = TvDataBase.searchForSeries(title, sortType, question3);
				}
				else if (question2.equals("y"))
				{
					displaySeries = MovieDatabase.searchForSeries(year, sortType, question3);
				}
				else if (question2.equals("b"))
				{
					displaySeries = MovieDatabase.searchForSeries(title, year, sortType, question3);
				}
			}
			

			//Display answers to user
			//TODO make this better
			JOptionPane.showMessageDialog(null, displayMovie.toString() + "\n" + displaySeries.toString());
			
			//Optional save data to file
			userInput = JOptionPane.showInputDialog(null, "Save data to file (y/n)?");
			if (userInput.equalsIgnoreCase("y"))
			{
				//Write data to specified file using the FileWriter and BufferedWriter objects
				//Haven't tested this yet but I'm pretty sure it will work
				saveFile = JOptionPane.showInputDialog(null, "Enter the name of the file you wish to save data to:");			
				FileWriter outfile = new FileWriter(saveFile);
				BufferedWriter bw = new BufferedWriter(outfile);
				
				//Check to see what ArrayList to get info from
				if (question1.equals("m") || question1.equals("b"))
				{
					for (int i = 0; i < displayMovie.size(); ++i)
					{
						bw.write(displayMovie.get(i).toString());
						bw.newLine();
					}
				}
				//Same as above. It is an if so the "b" will work (i.e. enter both conditionals if question1 = "b")
				if (question1.equals("s") || question1.equals("b"))
				{
					for (int i = 0; i < displaySeries.size(); ++i)
					{
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
		}
	}

}
