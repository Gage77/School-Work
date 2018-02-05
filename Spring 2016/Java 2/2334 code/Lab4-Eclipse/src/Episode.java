import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Lab #4 CS 2334, Section 0?? February 2?, 2016
 * <P>
 * This class models an episode of a series.
 * </P>
 * 
 * @author
 * @version 1.0
 */
public class Episode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of the Episode */
	String name;

	/** Season number of the Episode */
	int seasonNumber;

	/** Episode number of the Episode */
	int episodeNumber;

	/** Year of the Episode */
	int year;

	/**
	 * This is the default constructor for Episode.
	 */
	Episode() {
		this.name = null;
		this.seasonNumber = 0;
		this.episodeNumber = 0;
		this.year = 0;
	}

	/**
	 * This is the constructor for Episode. It instantiates the class with user
	 * supplied values.
	 * 
	 * @param name
	 * @param seasonNumber
	 * @param episodeNumber
	 * @param year
	 */
	public Episode(String name, int seasonNumber, int episodeNumber, int year) {
		// TODO This constructor really should check the parameters for
		// reasonable values.
		// For example, name should not be null or "" and the remaining
		// parameters should be positive.
		this.name = name;
		this.seasonNumber = seasonNumber;
		this.episodeNumber = episodeNumber;
		this.year = year;
	}
	
	public static void writeEpisode(String filename, Episode episode) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);
		
		objectOutputStream.writeObject(episode);
		objectOutputStream.close();
	}
	
	public static Episode readEpisode(String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		Episode episode = (Episode) objectInputStream.readObject();
		objectInputStream.close();
		return episode;
	}

	/**
	 * This method returns the attributes of this class as a single string.
	 * </P>
	 * 
	 * @return String representing the contents of this object.
	 */
	public String toString() {
		String attributesAsString = "EPISODE: ";
		if (this.name != null) {
			attributesAsString += this.name + ", ";
		}
		else {
			attributesAsString += "[untitled], ";
		}
		if (this.seasonNumber > 0 && this.episodeNumber > 0) {
			attributesAsString += "(" + seasonNumber + ", " + episodeNumber + "), ";
		}
		if (this.year > 0) {
			attributesAsString += this.year;
		}
		return (attributesAsString);
	}
}