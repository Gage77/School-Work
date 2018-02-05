import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * This program will create a small version of a media database, used to 
 * search for various forms of media and learn about them.
 * This program will focus on movies only.
 *
 */

public class Main 
{

	/**
	 * @param args Program arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		
		String fileName = args[0]; //movie file that will be added as a program arg
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = null; //Line containing individual movie info
		ArrayList<String> foundMovies = new ArrayList<String>(); //List of movies matching user parameter
		int parameterType = 0; //Title, Year, Form
		String userInput = "";
		
		Database database = new Database();		
		
		while ((line = br.readLine()) != null) //getting null pointer exception
		{
			Movie m = new Movie(line);
			database.insertMovie(m);
		}
		
		int n = JOptionPane.showConfirmDialog(null, "Welcome to the MDb simple user interface system. \n"
				+ "This system allows you to search our database \n of movies to learn information "
				+ "about that movie, \n such as its release year, format of release, \n and if there "
				+ "were multiple movies of that \n title released in that year. Click Ok to \n search for a "
				+ "movie or Cancel to exit the interface.", "MDb Simple User Interface System", JOptionPane.OK_CANCEL_OPTION);
		
		//If they wish to use the system and search for a movie
		while (n == JOptionPane.OK_OPTION)
		{
			//Figure out parameter type
			String userParameter = JOptionPane.showInputDialog("Do you wish to search by: \n Title \n Release year \n Release format");
			if (userParameter.equalsIgnoreCase("Title"))
			{
				userInput = JOptionPane.showInputDialog("Enter title or part of title of movie you wish to search for (Case sensitive): ");
				parameterType = 1;
			}
			else if (userParameter.equalsIgnoreCase("Release year") || userParameter.equalsIgnoreCase("year"))
			{
				userInput = JOptionPane.showInputDialog("Enter release year: ");
				parameterType = 2;
			}
			else if (userParameter.equalsIgnoreCase("Release format") || userParameter.equalsIgnoreCase("format"))
			{
				userInput = JOptionPane.showInputDialog("Enter format of release (Case sensitive): ");
				parameterType = 3;
			}
			
			//actual search for movie based on parameter
			database.printInfo(userInput, foundMovies, parameterType);
			System.out.println(foundMovies.toString());
			
			JOptionPane.showMessageDialog(null, "found movies: \n" + foundMovies.toString(), "MDb Simple User Interface System", JOptionPane.OK_OPTION);
			n = JOptionPane.showConfirmDialog(null, "Continue searching for movie?", "MDb Simple User Interface System", JOptionPane.OK_CANCEL_OPTION);
		}
			JOptionPane.showMessageDialog(null, "Thank you. Goodbye.");
		
		br.close();
		
	}
	
}
