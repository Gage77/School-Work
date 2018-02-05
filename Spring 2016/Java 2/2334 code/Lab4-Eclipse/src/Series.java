import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Lab #4 CS 2334, Section 0?? February 2?, 2016
 * <P>
 * This class models a series (such as a TV series).
 * </P>
 * 
 * @author
 * @version 1.0
 */

public class Series implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of the Series */
	private String name;

	/** Year that the Series started */
	private int startYear;

	/** Year that the Series ended */
	private int endYear;

	/** Episodes of the Series */
	private Episodes episodes;

	/**
	 * This is the default constructor for Series.
	 */
	public Series() 
	{
		name = "";
		startYear = 0;
		endYear = 0;
		episodes = new Episodes();
	}

	/**
	 * This is the constructor for Series. It instantiates the class with user
	 * supplied values.
	 * <P>
	 * 
	 * @param name
	 *            The name of the Series
	 * @param startYear
	 *            The startYear that the Series aired
	 * @param episodes
	 *            The episodes of the Series
	 */
	public Series(String name, int startYear, int endYear, Episodes episodes) 
	{
		this.name = name;
		this.startYear = startYear;
		this.endYear = endYear;
		this.episodes = episodes;
	}
	
	public static void writeSeries(String filename, Series series) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(series);
		objectOutputStream.close();
	}
	
	public static Series readSeries(String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		Series series = (Series) objectInputStream.readObject();
		objectInputStream.close();
		return series;
		
	}

	/**
	 * This method returns the attributes of this class as a single string. </P>
	 * 
	 * @return String representing the contents of this object.
	 */
	public String toString() 
	{
		String attributesAsString = "Series: " + this.name + ", " + this.startYear
				+ "-" + this.endYear + "\n"	+ this.episodes;
		return (attributesAsString);
	}
}