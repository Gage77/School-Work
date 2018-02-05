import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Project #2
 * CS 2334, Section 010 - Lab 13
 * Feb 19, 2016
 * <P>
 * This is a class that contains constructors for Series objects. Contains a method to read in
 * from a text file. Contains a method to parse Strings into separate String variable used to
 * construct objects. Contains get methods to return variable values. Contains a compareTo
 * method since it implements Comparable to compare objects. Contains a subclass to compare
 * Series objects by year instead of title.
 * </P>
 * @version 1.0
 */
public class Series implements Comparable<Series>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seriesTitle;
	private String seriesStartYear;
	private String seriesEndYear;
	private static ArrayList<Episode> episodes = new ArrayList<Episode>();
	private String[] seriesInfo;
	
	/**
	 * Constructor for Series object when only two of the components are present in the title
	 * @param seriesTitle The title of the series
	 * @param seriesYear The year the series debutted
	 */
	public Series(String seriesTitle, String seriesStartYear, String seriesEndYear, 
			ArrayList<Episode> TVList) {
		this.seriesTitle = seriesTitle;
		this.seriesStartYear = seriesStartYear;
		this.seriesEndYear = seriesEndYear;
		Series.episodes = TVList;
	}
	
	public static ArrayList<Episode> episodeList(String seriesTitle, ArrayList<Episode> TVList){
		for(int count = 0; count < TVList.size(); ++count){
			if(TVList.get(count).getSeries() == seriesTitle){
				episodes.add(TVList.get(count));
			}
		}
		return TVList;
	}
	
	/**
	 * Returns the value stored in seriesTitle 
	 * @return Returns the value stored in a String variable
	 * <dd>PRE - a String variable seriesTitle exists
	 * <dd>POST - Returns the String stored in the variable seriesTitle
	 */
	public String getSeriesTitle() {
		return seriesTitle;
	}
	

	/**
	 * Returns the value stored in seriesYear 
	 * @return Returns the value stored in a String variable
	 * <dd>PRE - a String variable seriesYear exists
	 * <dd>POST - Returns the String stored in the variable seriesYear
	 */
	public String getSeriesStartYear() {
		return seriesStartYear;
	}
	
	/**
	 * Returns the value stored in seriesYear 
	 * @return Returns the value stored in a String variable
	 * <dd>PRE - a String variable seriesYear exists
	 * <dd>POST - Returns the String stored in the variable seriesYear
	 */
	public String getSeriesEndYear() {
		return seriesEndYear;
	}

	/**
	 * Adds an episode to this series
	 * 
	 * @param newEp the episode to be added
	 */
	public void add(Episode newEp) {
		
		episodes.add(newEp);
		
	}
	

	/**
	 * @return episodes
	 */
	public ArrayList<Episode> getEpisodes() {
		
		return episodes;
		
	}
	
	/**
	 * Reads information in file into an Array of Strings as one per line in document
	 * as a solid string using FileReader and BufferedReader
	 * @param seriesInfo2 - an Array of Strings used to store contents of the text file
	 * @return The method has a void return type because the method just reads the text file 
	 * and stores its contents in an Array of Strings, one series per array element
	 * @exception IOException must be thrown by any method that does input/output,
	 * this method does input using FileReader and BufferedReader
	 * <dd>PRE - There must be an Array of Strings so that the method will have a place to
	 * store input
	 * <dd>POST - The method reads information from a text file and stores the 
	 * information in it into an Array of Strings, one line per element, which is to later be
	 * parsed into separate variables
	 */
	public static void readFile(ArrayList<String> seriesInfo, String filename) throws IOException {
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String nextLine = "";
		while(nextLine != null){
			nextLine = br.readLine();
			if(nextLine != null){
				seriesInfo.add(nextLine);		
			}
		}
		br.close();
		Collections.sort(seriesInfo);	
	}
	
	/**
	 * Parses a String Array of series information one element at a time into different
	 * information to be stored in variables. These variables are then used to construct
	 * Series objects to be stored in an ArrayList. Parses using substring.
	 * @param seriesInfo - A String Array containing the series information held on a single line
	 * for a series from the text file 
	 * @return The method has a void return type because the method just parses elements of a
	 * String Array into Strings and assigns them to variables, each a different 
	 * component of the series
	 * <dd>PRE - There must be an Array of series information, the elements of this Array are 
	 * set to a String variable, one per line in the text file
	 * <dd>POST - The method parses the elements of a String Array into String variables, one
	 * for each of the components a series in the text file may have so that they can be 
	 * used to construct a Series object
	 */
	public static void parseSeries(ArrayList<String> seriesInfo, ArrayList<Series> seriesList
			, ArrayList<Episode> TVList) {
		String[] tempArray;
		String[] yearTemp;
		String tempStartYear;
		String tempEndYear;
		String tempTitle;
		
		for(int index = 0; index < seriesInfo.size(); ++index){
			tempArray = seriesInfo.get(index).split("[\\s\\xA0]+");
			if(tempArray[tempArray.length - 1].contains("-")){
				yearTemp = tempArray[tempArray.length - 1].split("-");
				tempStartYear = yearTemp[0];
				tempEndYear = yearTemp[1];
				
				int endOfTitle = Arrays.asList(tempArray).indexOf("(" + tempStartYear + ")");
				tempTitle = tempArray[0];
				for(int count = 1; count < endOfTitle; ++count){
					tempTitle = tempTitle + " " + tempArray[count];
				}
				seriesList.add(new Series(tempTitle, tempStartYear, tempEndYear
						, episodeList(tempTitle, TVList)));
			}
		}
	}
	
	/**
	 * This method from the comparable interface will compare the Series object
	 * titles and see if there is a partial or complete match
	 * @param movie - This is a Series object that this method will use for comparison
	 * @return int - a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object
	 * <dt><b>Conditions:</b>
	 * <dd>PRE - Receives Series object to compare
	 * <dd>POST - Returns an integer number to say if the titles match
	 */
	@Override
	public int compareTo(Series series) {
		return this.seriesTitle.compareTo(series.getSeriesTitle());
	}
	
	/**
	 * This is a subclass that compares Series objects' year values and a returns a 
	 * negative integer, zero, or a positive integer as the first argument is less 
	 * than, equal to, or greater than the second. Used to sort seriesList by year.
	 */
	public static final Comparator<Series> SERIES_YEAR_COMPARATOR = new Comparator<Series>() {
		public int compare(Series seriesA, Series seriesB) {
			if (Integer.parseInt(seriesA.getSeriesStartYear()) > Integer.parseInt(seriesB.getSeriesStartYear())) {
				return 1;
			} else if (Integer.parseInt(seriesA.getSeriesStartYear()) < Integer.parseInt(seriesB.getSeriesStartYear())) {
				return -1;
			} else return 0;
		}
	};
	
	public static void writeSeries(String filename, Series series) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);
		
		objectOutputStream.writeObject(series);
		for (int i = 0; i < episodes.size(); ++i)
		{
			objectOutputStream.writeObject(episodes.get(i));
		}
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
}