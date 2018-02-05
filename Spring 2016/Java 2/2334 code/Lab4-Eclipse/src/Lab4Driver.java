import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Lab #4 CS 2334, Section 013 February 25, 2016
 * Hunter Black, Eric Morales, Ramon Valenzuela
 * <P>
 * This class implements a program that tests the Series and Episode classes.
 * </P>
 * 
 * @author
 * @version 1.0
 */
public class Lab4Driver 
{
	/**
	 * This is the main method for this test program. Since this is a simple
	 * test program, a lot of our code will be in the main method. Typically this
	 * would be a bad design, but we are just testing out some features of Java.
	 * <P>
	 * 
	 * @param args
	 *            Contains the command line arguments.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
			String filename = "file.txt";
		// --- Step 8 ---
			String name = "hello";
			int snumber = 1;
			int enumber = 1;
			int year = 2016;
		// Create an Episode object
			Episode episode = new Episode(name, snumber, enumber, year);
		// Write the Episode to a file
			Episode.writeEpisode(filename, episode);
		// Set the Episode object to null
			episode = null;
		// Print the Episode object to the console (show that the object is null)
			System.out.println(episode);
		// Read the Episode from a file
			episode = Episode.readEpisode(filename);
		// Print the Episode to the console
			System.out.println(episode);

		// --- Step 11 ---

		// Create five more Episode objects
			Episode a = new Episode("goodbye", 1, 2, 2016);
			Episode b = new Episode("blah", 1, 3, 2016);
			Episode c = new Episode("uieueue", 1, 4, 2016);
			Episode d = new Episode("floop", 1, 5, 2016);
			Episode f = new Episode("deflee", 1, 6, 2016);
		// Create a Episodes (plural) object
			Episodes episodes = new Episodes();
		// Add the six Episode objects to the Episodes object
			episodes.addEpisode(episode);
			episodes.addEpisode(a);
			episodes.addEpisode(b);
			episodes.addEpisode(c);
			episodes.addEpisode(d);
			episodes.addEpisode(f);
		// Write the Episodes object to a file
			writeEpisodes(filename, episodes);
		// Set the Episodes object to null
			episodes = null;
		// Print the Episodes object to the console (show that the object is empty)
			System.out.println(episodes);
		// Read in the Episodes object from a file
			episodes = readEpisodes(filename);
		// Print the Episodes object to the console
			System.out.println(episodes);
		
		// --- Bonus ---
			
			Series series = new Series("Doctor Who", 1965, 1985, episodes);
			Series.writeSeries(filename, series);
			series = null;
			System.out.println(series);
			series = Series.readSeries(filename);
			System.out.println(series);

	}
	
	public static void writeEpisodes(String filename, Episodes episodes) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(episodes);
		objectOutputStream.close();
	}
	
	public static Episodes readEpisodes(String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		Episodes episodes = (Episodes) objectInputStream.readObject();
		
		objectInputStream.close();
		return episodes;		
	}
	
}