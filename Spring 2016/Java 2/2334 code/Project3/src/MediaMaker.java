import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class will instantiate MediaMaker objects which contain
 * information about a specific person such as their name, which
 * number of that name they are, their acting credentials (if any),
 * their directing credentials (if any), and their producing credentials
 * (if any)
 */
public class MediaMaker implements Comparable<MediaMaker>,  Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Series series;
	/**	The name of the MediaMaker */
	private String name;
	/**	The number of that name they are of all MediaMaker objects */
	private String multiple;
	/** ArrayList of acting credits, with each line being a single credit */
	private ArrayList<String> actorInfo = new ArrayList<String>();
	/** ArrayList of directing credits, with each line being a single credit */
	private ArrayList<String> directorInfo = new ArrayList<String>();
	/** ArrayList of producing credits, with each line being a single credit */
	private ArrayList<String> producerInfo = new ArrayList<String>();
	
	private String actingInfo = "ACTING: \n";
	private String directingInfo = "DIRECTING: \n";
	private String producingInfo = "PRODUCING: \n";
	
	/**
	 * The base constructor for this class with no parameters
	 */
	public MediaMaker()
	{
		
	}
	
	/**
	 * The main constructor for this class which takes in a single
	 * String containing all information on a specific person and 
	 * passes it onto the parse method
	 * 
	 * @param info	String containing all information on the MediaMaker
	 */
	public MediaMaker(String names)
	{
		this.name = names;
	}
	
	/**
	 * @return		The last name of the MediaMaker
	 */
	public String getName()
	{
		return name;
	}
	
	public void addInfo(String param, int listType)
	{
		String[] a = param.split("\t");
		String b = "";
		
		b = a[a.length - 1];
		
		//0 = actor data
		if (listType == 0)
		{
			if (!actorInfo.contains(b))
				actorInfo.add(b);
		}
		//1 = director data
		if (listType == 1)
		{
			if (!directorInfo.contains(b))
				directorInfo.add(b);
		}
		//2 = producer data
		if (listType == 2)
		{
			if (!producerInfo.contains(b))
				producerInfo.add(b);
		}
	}
	
	/**
	 * This class will take the ArrayList's information and put them into single strings
	 * to make it easier to display later on
	 */
	public void parseAllInfo()
	{
		for (int i = 0; i < actorInfo.size(); ++i)
		{
			String line = actorInfo.get(i);
			if (line.charAt(0) != '"')
			{
				actingInfo += "MOVIE: " + line + "\n";
			}
			else if (line.charAt(0) == '"')
			{
				actingInfo += "SERIES: " + line + "\n";
			}	
		}
		
		for (int i = 0; i < directorInfo.size(); ++i)
		{
			String line = directorInfo.get(i);
			if (line.charAt(0) != '"')
			{
				directingInfo += "MOVIE: " + line + "\n";
			}
			else if (line.charAt(0) == '"')
			{
				directingInfo += "SERIES: " + line + "\n";
			}
		}
		
		for (int i = 0; i < producerInfo.size(); ++i)
		{
			String line = producerInfo.get(i);
			if (line.charAt(0) != '"')
			{
				producingInfo += "MOVIE: " + line + "\n";
			}
			else if (line.charAt(0) == '"')
			{
				producingInfo += "SERIES: " + line + "\n";
			}
		}
	}
	
	/**
	 * @return		The acting credentials of the MediaMaker (if any)
	 */
	public String getActingInfo()
	{
		return this.actingInfo;
	}
	
	/**
	 * @return		The directing credentials of the MediaMaker (if any)
	 */
	public String getDirectingInfo()
	{
		return this.directingInfo;
	}
	
	/**
	 * @return		The producing credentials of the MediaMaker (if any)
	 */
	public String getProducingInfo()
	{
		return this.producingInfo;
	}
	/**
	 * returns an int array containing the number of credits for acting,
	 * producing, and directing for the specified MediaMaker to be used
	 * in the graphical display. The indexes correspond as follows:
	 * 0: acting credits
	 * 1: directing credits
	 * 2: producing credits
	 * 
	 * @param m		MediaMaker to check
	 * @return		int array containing acting, directing, producing info
	 */
	public int[] getMediaMakerNumberInfo()
	{
		int[] a = {actorInfo.size(), directorInfo.size(), producerInfo.size()};
		return a;
	}
	
	/**
	 * @override	toString
	 * 
	 * @return		String representation of the MediaMaker object
	 * 				fit for display to the user
	 */
	public String toString()
	{
		String minus = "-";
		for (int i = 0; i < 80; ++i)
		{
			minus += "-";
		}
		
		String fullReturn = this.name + "\n" + this.actingInfo + this.directingInfo + this.producingInfo + "\n" + minus;
		
		return fullReturn;
	}

	@Override
	/**
	 * Overrides the interface's compareTo method
	 */
	public int compareTo(MediaMaker m) 
	{
		int nameDiff = this.name.toLowerCase().compareTo(m.getName().toLowerCase());
		if (nameDiff != 0)
			return nameDiff;
		
		return 0;
	}
	
	public static void writeMediaMaker(String filename, MediaMaker m) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);
		
		objectOutputStream.writeObject(m.toString());
		objectOutputStream.close();
	}
	
	public static MediaMaker readMediaMaker(String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		MediaMaker w = (MediaMaker) objectInputStream.readObject();
		objectInputStream.close();
		return w;
	}

}
